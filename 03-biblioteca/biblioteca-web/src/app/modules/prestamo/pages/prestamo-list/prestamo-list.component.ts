import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Prestamo } from '../../model/prestamo.model';
import { PrestamoService } from '../../services/prestamo.service';

@Component({
  selector: 'app-prestamo-list',
  templateUrl: './prestamo-list.component.html',
  styleUrl: './prestamo-list.component.css'
})
export class PrestamoListComponent implements OnInit {

  prestamos: Prestamo[] = [];
  prestamosPaginados: any[] = [];
  prestamoSeleccionado: Prestamo | null = null;
  prestamoIdEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private prestamoService: PrestamoService) { }

  ngOnInit(): void {
    this.cargarPrestamos();
  }

  cargarPrestamos(): void {
    this.isLoading = true;

    this.prestamoService.obtenerPrestamos().subscribe({
      next: (data) => {
        console.log("Prestamos: ", data);
        this.prestamos = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);
      },
      error: (e) => {
        console.log("Error cargando prestamos: ", e);
        this.isLoading = false;
      }
    });
  }

  onPageChange(data: any[]) {
    this.prestamosPaginados = data;
  }

  limpiarBusqueda(): void {
    this.searchControl.reset('');
    this.cargarPrestamos();
  }

  openModal(prestamo?: Prestamo): void {
    this.prestamoSeleccionado = prestamo ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.prestamoSeleccionado = null;
  }

  onSave(): void {
    this.closeModal();
    this.cargarPrestamos();
  }

  eliminarPrestamo(id: number): void {
    this.prestamoIdEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean) {
    this.showConfirm = false;

    if (confirmed && this.prestamoIdEliminar !== null) {
      this.prestamoService.eliminarPrestamo(this.prestamoIdEliminar).subscribe({
        next: () => this.cargarPrestamos(),
        error: (error) => console.log('Error eliminando la prestamo ', error),
        complete: () => (this.prestamoIdEliminar = null)
      });
    } else {
      this.prestamoIdEliminar = null;
    }
  }
}
