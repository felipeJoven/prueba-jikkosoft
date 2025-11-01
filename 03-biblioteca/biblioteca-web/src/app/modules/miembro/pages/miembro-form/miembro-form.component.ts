import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Miembro } from '../../../miembro/model/miembro.model';
import { MiembroService } from '../../services/miembro.service';


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
  // tipoIdentificacion: TipoIdentificacion[] = [];
  // biblioteca: Biblioteca[] = [];

  constructor(
    private fb: FormBuilder,
    private miembroService: MiembroService,
  ) {
    this.miembroForm = this.fb.group({
      titulo: ['', Validators.required],     
    isbn: ['', Validators.required],
    categoriaId: [0, Validators.required],
    });
  }

  ngOnInit(): void {
    // this.loadCategories();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['miembro'] && this.miembro) {
      this.miembroForm.patchValue(this.miembro);
    } else {
      this.miembroForm.reset();
    }
  }

  // private loadCategories(): void {
  //   this.categoriaService.obtenerCategorias().subscribe({
  //     next: (data) => {
  //       console.log("Respuesta del backend:", data);
  //       this.categoria = data
  //     },
  //     error: (e) => console.log("Error cargando tipos de miembro: ", e)

  //   });
  // }

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
