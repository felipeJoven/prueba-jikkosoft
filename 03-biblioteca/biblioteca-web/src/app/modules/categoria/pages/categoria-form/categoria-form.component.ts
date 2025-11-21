import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Categoria } from '../../model/categoria.model';
import { CategoriaService } from '../../service/categoria.service';

@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.component.html',
  styleUrl: './categoria-form.component.css'
})
export class CategoriaFormComponent implements OnChanges {

  @Input() categoria: Categoria | null = null;

  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  categoriaForm: FormGroup;

  constructor(private fb: FormBuilder, private categoriaService: CategoriaService) {
    this.categoriaForm = this.fb.group({
      nombre: ['', Validators.required]
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['categoria'] && this.categoria) {
      this.categoriaForm.patchValue(this.categoria);
    } else {
      this.categoriaForm.reset();
    }
  }

  onSubmit(): void {
    if (this.categoriaForm.valid) {
      const formValue = this.categoriaForm.value;
      const id = this.categoria?.id;

      this.categoriaService.guardarCategoria(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando libro: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
