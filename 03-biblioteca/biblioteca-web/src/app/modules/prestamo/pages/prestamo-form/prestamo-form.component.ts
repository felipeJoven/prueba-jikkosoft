import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoriaService } from '../../../categoria/service/categoria.service';
import { Libro } from '../../../libro/model/libro.model';
import { Prestamo } from '../../model/prestamo.model';
import { PrestamoService } from '../../services/prestamo.service';
import { Miembro } from '../../../miembro/model/miembro.model';

@Component({
  selector: 'app-prestamo-form',
  templateUrl: './prestamo-form.component.html',
  styleUrl: './prestamo-form.component.css'
})
export class PrestamoFormComponent {
  
    @Input() prestamo: Prestamo | null = null;
    @Output() guardado = new EventEmitter<void>();
    @Output() cancelar = new EventEmitter<void>();
  
    prestamoForm: FormGroup;
    libro: Libro[] = [];
    miembro: Miembro[] = [];
  
    constructor(
      private fb: FormBuilder,
      private prestamoService: PrestamoService,
      private categoriaService: CategoriaService
    ) {
      this.prestamoForm = this.fb.group({
        titulo: ['', Validators.required],     
      isbn: ['', Validators.required],
      categoriaId: [0, Validators.required],
      });
    }
  
    ngOnInit(): void {
      this.loadCategories();
    }
  
    ngOnChanges(changes: SimpleChanges): void {
      if (changes['prestamo'] && this.prestamo) {
        this.prestamoForm.patchValue(this.prestamo);
      } else {
        this.prestamoForm.reset();
      }
    }
  
    private loadCategories(): void {
      // this.categoriaService.obtenerCategorias().subscribe({
      //   next: (data) => {
      //     console.log("Respuesta del backend:", data);
      //     this.categoria = data
      //   },
      //   error: (e) => console.log("Error cargando tipos de prestamo: ", e)
  
      // });
    }
  
    onSubmit(): void {
      if (this.prestamoForm.valid) {
        const formValue = this.prestamoForm.value;
        const id = this.prestamo?.id;
  
        this.prestamoService.guardarPrestamo(formValue, id).subscribe({
          next: () => this.guardado.emit(),
          error: (e) => console.error("Error guardando prestamo: ", e)
        });
      }
    }
  
    onCancel(): void {
      this.cancelar.emit();
    }
}
