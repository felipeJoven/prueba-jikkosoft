import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Miembro } from '../../models/miembro.model';
import { MiembroService } from '../../services/miembro.service';

import { Biblioteca } from '../../../biblioteca/model/biblioteca.model';
import { TipoIdentificacion } from '../../models/tipo-identificacion.model';
import { TipoIdentificacionService } from '../../services/tipo-identificacion.service';
import { BibliotecaService } from '../../../biblioteca/services/biblioteca.service';


@Component({
  selector: 'app-miembro-form',
  templateUrl: './miembro-form.component.html',
  styleUrls: ['./miembro-form.component.css']
})
export class MiembroFormComponent implements OnInit, OnChanges {

  @Input() miembro: Miembro | null = null;

  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  miembroForm: FormGroup;
  tiposIdentificacion: TipoIdentificacion[] = [];
  bibliotecas: Biblioteca[] = [];
  nombresBibliotecas: string[] = [];
  bibliotecaSeleccionada: string = '';

  constructor(
    private fb: FormBuilder,
    private miembroService: MiembroService,
    private tipoService: TipoIdentificacionService,
    private bibliotecaService: BibliotecaService
  ) {
    this.miembroForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      numeroIdentificacion: ['', Validators.required],
      telefono: ['', Validators.required],
      tipoIdentificacionId: [null, Validators.required],
      bibliotecaId: [null, Validators.required],
      activo: [true]
    });
  }

  ngOnInit(): void {
    this.cargarTiposIdentificacion();
    this.cargarBibliotecas();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['miembro'] && this.miembro) {
      this.miembroForm.patchValue(this.miembro);
      this.sincronizarBiblioteca();
    } else {
      this.miembroForm.reset();
    }
  }

  private cargarTiposIdentificacion(): void {
    this.tipoService.obtenerTiposIdentificacion().subscribe({
      next: (data) => {
        this.tiposIdentificacion = data
      },
      error: (e) => console.log("Error cargando tipos de identificación: ", e)
    });
  }

  private cargarBibliotecas(): void {
    this.bibliotecaService.obtenerBibliotecas().subscribe({
      next: (data) => {
        this.bibliotecas = data;
        this.nombresBibliotecas = data.map(b => b.nombre);
        this.sincronizarBiblioteca();
      },
      error: (e) => console.error("Error cargando bibliotecas: ", e)
    });
  }

  private sincronizarBiblioteca(): void {
    if (!this.miembro || this.bibliotecas.length === 0) return;

    const biblioteca = this.bibliotecas.find(b => b.id === this.miembro?.bibliotecaId);
    this.bibliotecaSeleccionada = biblioteca ? biblioteca.nombre : '';
  }

  onSelectBiblioteca(nombre: string) {
    const biblioteca = this.bibliotecas.find(b => b.nombre === nombre);

    this.miembroForm.patchValue({
      bibliotecaId: biblioteca ? biblioteca.id : null
    });

  }

  onSubmit(): void {
    if (this.miembroForm.invalid) return;

    const formData = this.miembroForm.value;
    const id = this.miembro?.id;

    this.miembroService.guardarMiembro(formData, id).subscribe({
      next: () => this.guardado.emit(),
      error: (e) => console.error("Error guardando miembro: ", e)
    });
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
