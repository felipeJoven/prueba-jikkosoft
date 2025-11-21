import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Libro } from '../../model/libro.model';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-libro-list',
  templateUrl: './libro-list.component.html',
  styleUrl: './libro-list.component.css'
})
export class LibroListComponent implements OnInit {

  libros: Libro[] = [];
  librosPaginados: any[] = [];
  libroSeleccionado: Libro | null = null;
  libroIdEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;
  
  searchControl = new FormControl('');

  constructor(private libroService: LibroService) { }

  ngOnInit(): void {
    this.cargarLibros();
  }

  cargarLibros(): void {
    this.isLoading = true;
    
    this.libroService.obtenerLibros().subscribe({
      next: (data) => {
        console.log("Libros: ", data);
        this.libros = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);        
      },
      error: (e) => {
        console.log("Error cargando libros: ", e);
        this.isLoading = false;
      }
    });
  }

  onPageChange(data: any[]) {
    this.librosPaginados = data;
  }

  limpiarBusqueda(): void {
    this.searchControl.reset('');
    this.cargarLibros();
  }

  openModal(libro?: Libro): void {
    this.libroSeleccionado = libro ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.libroSeleccionado = null;
  }

  onSave(): void {
    this.closeModal();
    this.cargarLibros();
  }

  eliminarLibro(id: number): void {
    this.libroIdEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {    
    this.showConfirm = false;
    
    if (confirmed && this.libroIdEliminar !== null) {
      this.libroService.eliminarLibro(this.libroIdEliminar).subscribe({
        next: () => this.cargarLibros(),
        error: (error) => console.log('Error eliminando el libro: ', error),
        complete: () => (this.libroIdEliminar = null)
      });
    } else {
      this.libroIdEliminar = null;
    }
  }
}
