import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Autor } from '../../model/autor.model';
import { AutorService } from '../../services/autor.service';

@Component({
  selector: 'app-autor-list',
  templateUrl: './autor-list.component.html',
  styleUrl: './autor-list.component.css'
})
export class AutorListComponent implements OnInit {

  autores: Autor[] = [];
  autoresPaginados: any[] = [];
  autorSeleccionado: Autor | null = null;
  autorIdEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private autorService: AutorService) { }

  ngOnInit(): void {
    this.cargarAutores();
  }

  cargarAutores(): void {
    this.isLoading = true;

    this.autorService.obtenerAutores().subscribe({
      next: (data) => {
        console.log("Autors: ", data);
        this.autores = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);
      },
      error: (e) => {
        console.log("Error cargando autores: ", e);
        this.isLoading = false;
      }
    });
  }

  onPageChange(data: any[]) {
    this.autoresPaginados = data;
  }

  limpiarBusqueda(): void {
    this.searchControl.reset('');
    this.cargarAutores();
  }

  openModal(autor?: Autor): void {
    this.autorSeleccionado = autor ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.autorSeleccionado = null;
  }

  onSave(): void {
    this.closeModal();
    this.cargarAutores();
  }

  eliminarAutor(id: number): void {
    this.autorIdEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {
    this.showConfirm = false;
    if (confirmed && this.autorIdEliminar !== null) {
      this.autorService.eliminarAutor(this.autorIdEliminar).subscribe({
        next: () => this.cargarAutores(),
        error: (error) => console.log('Error eliminando la autor: ', error),
        complete: () => (this.autorIdEliminar = null)
      });
    } else {
      this.autorIdEliminar = null;
    }
  }
}
