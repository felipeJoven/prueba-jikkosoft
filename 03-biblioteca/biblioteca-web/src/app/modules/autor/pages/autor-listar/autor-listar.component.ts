import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Autor } from '../../model/autor.model';
import { AutorService } from '../../services/autor.service';

@Component({
  selector: 'app-autor-listar',
  templateUrl: './autor-listar.component.html',
  styleUrl: './autor-listar.component.css'
})
export class AutorListarComponent implements OnInit {

  autores: Autor[] = [];
  autoresPaginados: any[] = [];
  autorSeleccionado: Autor | null = null;
  autorEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private autorService: AutorService) { }

  ngOnInit(): void {
    this.loadAuthors();
  }

  loadAuthors(): void {
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

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadAuthors();
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
    this.loadAuthors();
  }

  deleteAuthors(id: number): void {
    this.autorEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {
    this.showConfirm = false;
    if (confirmed && this.autorEliminar !== null) {
      this.autorService.eliminarAutor(this.autorEliminar).subscribe({
        next: () => this.loadAuthors(),
        error: (error) => console.log('Error eliminando la autor: ', error),
        complete: () => (this.autorEliminar = null)
      });
    } else {
      this.autorEliminar = null;
    }
  }
}
