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
  categoriaIdEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  private cargarCategorias() {
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

  limpiarBusqueda(): void {
    this.searchControl.reset('');
    this.cargarCategorias();
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
    this.cargarCategorias();
  }

  eliminarCategoria(id: number): void {
    this.categoriaIdEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {
    this.showConfirm = false;
    if (confirmed && this.categoriaIdEliminar !== null) {
      this.categoriaService.eliminarCategoria(this.categoriaIdEliminar).subscribe({
        next: () => this.cargarCategorias(),
        error: (error) => console.log('Error eliminando el libro: ', error),
        complete: () => (this.categoriaIdEliminar = null)
      });
    } else {
      this.categoriaIdEliminar = null;
    }
  }
}
