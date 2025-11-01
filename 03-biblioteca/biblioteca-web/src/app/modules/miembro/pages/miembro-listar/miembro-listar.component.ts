import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Miembro } from '../../model/miembro.model';
import { MiembroService } from '../../services/miembro.service';

@Component({
  selector: 'app-miembro-listar',
  templateUrl: './miembro-listar.component.html',
  styleUrl: './miembro-listar.component.css'
})
export class MiembroListarComponent implements OnInit {

  miembros: Miembro[] = [];
  miembrosPaginados: any[] = [];
  miembroSeleccionado: Miembro | null = null;

  isLoading = true;
  showModal = false;
  errorMessage = '';
  
  searchControl = new FormControl('');

  constructor(private miembroService: MiembroService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.isLoading = true;
    
    this.miembroService.obtenerMiembros().subscribe({
      next: (data) => {
        console.log("Miembros: ", data);
        this.miembros = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);        
      },
      error: (e) => {
        console.log("Error cargando miembros: ", e);
        this.isLoading = false;
      }
    });

  }

  onPageChange(data: any[]) {
    this.miembrosPaginados = data;
  }

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadBooks();
  }

  openModal(miembro?: Miembro): void {
    this.miembroSeleccionado = miembro ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.miembroSeleccionado = null;
  }

  onSave(): void {
    this.closeModal();
    this.loadBooks();
  }

  deleteBooks(id: number): void {
    this.miembroService.eliminarMiembro(id).subscribe({
      next: () => this.loadBooks(),
      error: (error) => console.log('Error eliminando la miembro ', error)
    });
  }
}
