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
  titulosLibros: string[] = [];
  libroSeleccionado: string = '';
  numerosIdMiembros: string[] = [];
  miembroSeleccionado: string = '';
  nombreMiembro: string = '';

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
    this.cargarLibros();
    this.cargarMiembros();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['prestamo'] && this.prestamo) {
      this.prestamoForm.patchValue(this.prestamo);
      this.sincronizarLibro();
      this.sincronizarMiembro();
    } else {
      this.prestamoForm.reset();
    }
  }

  private cargarLibros(): void {
    this.libroService.obtenerLibros().subscribe({
      next: (data) => {
        this.libros = data
        this.titulosLibros = data.map(l => l.titulo);
        this.sincronizarLibro();
      },
      error: (e) => console.log("Error cargando tipos de prestamo: ", e)

    });
  }

  private cargarMiembros(): void {
    this.miembroService.obtenerMiembros().subscribe({
      next: (data) => {
        this.miembros = data
        this.numerosIdMiembros = data.map(m => m.numeroIdentificacion);
        this.sincronizarMiembro();
      },
      error: (e) => console.log("Error cargando tipos de prestamo: ", e)

    });
  }

  private sincronizarLibro(): void {
    if (!this.prestamo || this.libros.length === 0) return;

    const biblioteca = this.libros.find(b => b.id === this.prestamo?.libroId);
    this.libroSeleccionado = biblioteca ? biblioteca.titulo : '';
  }

  private sincronizarMiembro(): void {
    if (!this.prestamo || this.miembros.length === 0) return;

    const biblioteca = this.miembros.find(b => b.id === this.prestamo?.miembroId);
    this.miembroSeleccionado = biblioteca ? biblioteca.numeroIdentificacion : '';
  }

  onSelectLibro(titulo: string) {
    const libro = this.libros.find(l => l.titulo === titulo);

    this.prestamoForm.patchValue({
      libroId: libro ? libro.id : null
    });

  }

  onSelectMiembro(numeroId: string) {
    const miembro = this.miembros.find(m => m.numeroIdentificacion === numeroId);

    this.prestamoForm.patchValue({
      miembroId: miembro ? miembro.id : null
    });

    this.nombreMiembro = miembro ? miembro.nombre + " " + miembro.apellido : '';
  }

  onSubmit(): void {
    if (this.prestamoForm.invalid) return;

      const formData = this.prestamoForm.value;
      const id = this.prestamo?.id;

      this.prestamoService.guardarPrestamo(formData, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando prestamo: ", e)
      });    
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
