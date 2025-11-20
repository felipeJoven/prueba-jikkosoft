import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Biblioteca } from '../../model/biblioteca.model';
import { BibliotecaService } from '../../services/biblioteca.service';

@Component({
  selector: 'app-biblioteca-list',
  templateUrl: './biblioteca-list.component.html',
  styleUrl: './biblioteca-list.component.css'
})
export class BibliotecaListComponent implements OnInit {

  bibliotecas: Biblioteca[] = [];
  bibliotecasPaginadas: any[] = [];
  bibliotecaSeleccionada: Biblioteca | null = null;
  bibliotecaEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;
  
  searchControl = new FormControl('');

  constructor(private bibliotecaService: BibliotecaService) { }

  ngOnInit(): void {
    this.loadLibraries();
  }

  loadLibraries(): void {
    this.isLoading = true;
    
    this.bibliotecaService.obtenerBibliotecas().subscribe({
      next: (data) => {
        console.log("Bibliotecas: ", data);
        this.bibliotecas = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);        
      },
      error: (e) => {
        console.log("Error cargando bibliotecas: ", e);
        this.isLoading = false;
      }
    });

  }

  onPageChange(data: any[]) {
    this.bibliotecasPaginadas = data;
  }

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadLibraries();
  }

  openModal(biblioteca?: Biblioteca): void {
    this.bibliotecaSeleccionada = biblioteca ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.bibliotecaSeleccionada = null;
  }

  onSave(): void {
    this.closeModal();
    this.loadLibraries();
  }

  deleteLibraries(id: number): void {
    this.bibliotecaEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean): void {
    this.showConfirm = false;
    if (confirmed && this.bibliotecaEliminar !== null) {
      this.bibliotecaService.eliminarBiblioteca(this.bibliotecaEliminar).subscribe({
        next: () => this.loadLibraries(),
        error: (error) => console.log('Error eliminando la biblioteca: ', error),
        complete: () => (this.bibliotecaEliminar = null)
      });
    } else {
      this.bibliotecaEliminar = null;
    }
  }
}
