import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Libro } from '../../model/libro.model';
import { LibroService } from '../../services/libro.service';
import { Categoria } from '../../../categoria/model/categoria.model';
import { CategoriaService } from '../../../categoria/service/categoria.service';

@Component({
  selector: 'app-libro-form',
  templateUrl: './libro-form.component.html',
  styleUrl: './libro-form.component.css'
})
export class LibroFormComponent implements OnInit, OnChanges {

  @Input() libro: Libro | null = null;
  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  libroForm: FormGroup;
  categoria: Categoria[] = [];

  constructor(
    private fb: FormBuilder,
    private libroService: LibroService,
    private categoriaService: CategoriaService
  ) {
    this.libroForm = this.fb.group({
      titulo: ['', Validators.required],     
    isbn: ['', Validators.required],
    categoriaId: [0, Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['libro'] && this.libro) {
      this.libroForm.patchValue(this.libro);
    } else {
      this.libroForm.reset();
    }
  }

  private loadCategories(): void {
    this.categoriaService.obtenerCategorias().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.categoria = data
      },
      error: (e) => console.log("Error cargando tipos de libro: ", e)

    });
  }

  onSubmit(): void {
    if (this.libroForm.valid) {
      const formValue = this.libroForm.value;
      const id = this.libro?.id;

      this.libroService.guardarLibro(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando libro: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
