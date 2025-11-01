import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Libro } from '../../model/libro.model';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-libro-listar',
  templateUrl: './libro-listar.component.html',
  styleUrl: './libro-listar.component.css'
})
export class LibroListarComponent implements OnInit {

  libros: Libro[] = [];
  librosPaginados: any[] = [];
  libroSeleccionado: Libro | null = null;

  isLoading = true;
  showModal = false;
  errorMessage = '';
  
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
    this.libroService.eliminarLibro(id).subscribe({
      next: () => this.loadBooks(),
      error: (error) => console.log('Error eliminando la libro ', error)
    });
  }
}
