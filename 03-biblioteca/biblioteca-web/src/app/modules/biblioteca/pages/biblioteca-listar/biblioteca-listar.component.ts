import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Biblioteca } from '../../model/biblioteca.model';
import { BibliotecaService } from '../../services/biblioteca.service';

@Component({
  selector: 'app-biblioteca-listar',
  templateUrl: './biblioteca-listar.component.html',
  styleUrl: './biblioteca-listar.component.css'
})
export class BibliotecaListarComponent implements OnInit {

  bibliotecas: Biblioteca[] = [];
  bibliotecasPaginadas: any[] = [];
  bibliotecaSeleccionada: Biblioteca | null = null;

  isLoading = true;
  showModal = false;
  errorMessage = '';
  
  searchControl = new FormControl('');

  constructor(private bibliotecaService: BibliotecaService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
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
    this.loadBooks();
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
    this.loadBooks();
  }

  deleteBooks(id: number): void {
    this.bibliotecaService.eliminarBiblioteca(id).subscribe({
      next: () => this.loadBooks(),
      error: (error) => console.log('Error eliminando la biblioteca ', error)
    });
  }
}
