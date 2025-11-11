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
  styleUrl: './miembro-form.component.css'
})
export class MiembroFormComponent implements OnInit, OnChanges {

  @Input() miembro: Miembro | null = null;
  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  miembroForm: FormGroup;
  tiposIdentificacion: TipoIdentificacion[] = [];
  bibliotecas: Biblioteca[] = [];

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
    });
  }

  ngOnInit(): void {
    this.loadTypes();
    this.loadLibraries();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['miembro'] && this.miembro) {
      this.miembroForm.patchValue(this.miembro);
    } else {
      this.miembroForm.reset();
    }
  }

  private loadTypes(): void {
    this.tipoService.obtenerTiposIdentificacion().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.tiposIdentificacion = data
      },
      error: (e) => console.log("Error cargando tipos de identificación: ", e)
    });
  }
  
  private loadLibraries(): void {
    this.bibliotecaService.obtenerBibliotecas().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.bibliotecas = data
      },
      error: (e) => console.log("Error cargando tipos de libros: ", e)
    });
  }

  onSubmit(): void {
    if (this.miembroForm.valid) {
      const formValue = this.miembroForm.value;
      const id = this.miembro?.id;

      this.miembroService.guardarMiembro(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando miembro: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
