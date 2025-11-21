import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Libro } from '../../model/libro.model';
import { LibroService } from '../../services/libro.service';
import { Autor } from '../../../autor/model/autor.model';
import { AutorService } from '../../../autor/services/autor.service';

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
  autores: Autor[] = [];
  nombresAutores: string[] = [];
  autorSeleccionado: string = '';

  constructor(
    private fb: FormBuilder,
    private libroService: LibroService,
    private autorService: AutorService
  ) {
    this.libroForm = this.fb.group({
      titulo: ['', Validators.required],     
      isbn: ['', Validators.required],
      autorId: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.cargarAutores();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['libro'] && this.libro) {
      this.libroForm.patchValue(this.libro);
      this.sincronizarAutor();
    } else {
      this.libroForm.reset();
    }
  }

  private cargarAutores(): void {
    this.autorService.obtenerAutores().subscribe({
      next: (data) => {
        this.autores = data
        this.nombresAutores = data.map(a => a.nombre);
        this.sincronizarAutor();
      },
      error: (e) => console.log("Error cargando tipos de libro: ", e)
    });
  }

  private sincronizarAutor(): void {
    if (!this.libro || this.autores.length === 0) return;

    const autor = this.autores.find(a => a.id === this.libro?.autorId);
    this.autorSeleccionado = autor ? autor.nombre : '';
  }

  onSelectAutor(nombre: string) {
    const autor = this.autores.find(a => a.nombre === nombre);

    this.libroForm.patchValue({
      autorId: autor ? autor.id : null
    });
  }

  onSubmit(): void {
    if (this.libroForm.invalid) return;
    
      const formData = this.libroForm.value;
      const id = this.libro?.id;

      this.libroService.guardarLibro(formData, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando libro: ", e)
      });
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
