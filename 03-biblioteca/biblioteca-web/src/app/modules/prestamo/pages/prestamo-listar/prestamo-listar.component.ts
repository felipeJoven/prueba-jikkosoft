import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Prestamo } from '../../model/prestamo.model';
import { PrestamoService } from '../../services/prestamo.service';

@Component({
  selector: 'app-prestamo-listar',
  templateUrl: './prestamo-listar.component.html',
  styleUrl: './prestamo-listar.component.css'
})
export class PrestamoListarComponent implements OnInit {

  prestamos: Prestamo[] = [];
  prestamosPaginados: any[] = [];
  prestamoSeleccionado: Prestamo | null = null;

  isLoading = true;
  showModal = false;
  errorMessage = '';
  
  searchControl = new FormControl('');

  constructor(private prestamoService: PrestamoService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
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

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadBooks();
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
    this.loadBooks();
  }

  deleteBooks(id: number): void {
    this.prestamoService.eliminarPrestamo(id).subscribe({
      next: () => this.loadBooks(),
      error: (error) => console.log('Error eliminando la prestamo ', error)
    });
  }
}
