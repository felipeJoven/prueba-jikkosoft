import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Prestamo } from '../../model/prestamo.model';
import { PrestamoService } from '../../services/prestamo.service';

import { Libro } from '../../../libro/model/libro.model';
import { LibroService } from '../../../libro/services/libro.service';
import { Miembro } from '../../../miembro/models/miembro.model';
import { MiembroService } from '../../../miembro/services/miembro.service';

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
  libros: Libro[] = [];
  miembros: Miembro[] = [];

  constructor(
    private fb: FormBuilder,
    private prestamoService: PrestamoService,
    private libroService: LibroService,
    private miembroService: MiembroService
  ) {
    this.prestamoForm = this.fb.group({
      fechaPrestamo: ['', Validators.required],
      fechaDevolucion: ['', Validators.required],
      libroId: [null, Validators.required],
      miembroId: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadBooks();
    this.loadMembers();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['prestamo'] && this.prestamo) {
      this.prestamoForm.patchValue(this.prestamo);
    } else {
      this.prestamoForm.reset();
    }
  }

  private loadBooks(): void {
    this.libroService.obtenerLibros().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.libros = data
      },
      error: (e) => console.log("Error cargando tipos de prestamo: ", e)

    });
  }

  private loadMembers(): void {
    this.miembroService.obtenerMiembros().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.miembros = data
      },
      error: (e) => console.log("Error cargando tipos de prestamo: ", e)

    });
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
