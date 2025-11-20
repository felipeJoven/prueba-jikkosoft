import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Categoria } from '../../model/categoria.model';
import { CategoriaService } from '../../service/categoria.service';

@Component({
  selector: 'app-categoria-list',
  templateUrl: './categoria-list.component.html',
  styleUrl: './categoria-list.component.css'
})
export class CategoriaListComponent implements OnInit {

  categorias: Categoria[] = [];
  categoriasPaginadas: any[] = [];
  categoriaSeleccionada: Categoria | null = null;
  categoriaEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  private loadCategories() {
    this.isLoading = true;

    this.categoriaService.obtenerCategorias().subscribe({
      next: (data) => {
        console.log("Libros: ", data);
        this.categorias = data;
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
    this.categoriasPaginadas = data;
  }

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadCategories();
  }

  openModal(categoria?: Categoria): void {
    this.categoriaSeleccionada = categoria ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.categoriaSeleccionada = null;
  }

  onSave(): void {
    this.closeModal();
    this.loadCategories();
  }

  deleteBooks(id: number): void {
    this.categoriaEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {
    this.showConfirm = false;
    if (confirmed && this.categoriaEliminar !== null) {
      this.categoriaService.eliminarCategoria(this.categoriaEliminar).subscribe({
        next: () => this.loadCategories(),
        error: (error) => console.log('Error eliminando el libro: ', error),
        complete: () => (this.categoriaEliminar = null)
      });
    } else {
      this.categoriaEliminar = null;
    }
  }
}
