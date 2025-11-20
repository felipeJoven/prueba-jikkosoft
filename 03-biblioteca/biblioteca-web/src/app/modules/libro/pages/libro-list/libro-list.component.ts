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
  libroEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;
  
  searchControl = new FormControl('');

  constructor(private libroService: LibroService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
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

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadBooks();
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
    this.loadBooks();
  }

  deleteBooks(id: number): void {
    this.libroEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {    
    this.showConfirm = false;
    
    if (confirmed && this.libroEliminar !== null) {
      this.libroService.eliminarLibro(this.libroEliminar).subscribe({
        next: () => this.loadBooks(),
        error: (error) => console.log('Error eliminando el libro: ', error),
        complete: () => (this.libroEliminar = null)
      });
    } else {
      this.libroEliminar = null;
    }
  }
}
