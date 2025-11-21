import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Miembro } from '../../models/miembro.model';
import { MiembroService } from '../../services/miembro.service';

@Component({
  selector: 'app-miembro-list',
  templateUrl: './miembro-list.component.html',
  styleUrl: './miembro-list.component.css'
})
export class MiembroListComponent implements OnInit {

  miembros: Miembro[] = [];
  miembrosPaginados: any[] = [];
  miembroSeleccionado: Miembro | null = null;
  miembroIdEliminar: number | null = null;

  errorMessage = '';
  isLoading = true;
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private miembroService: MiembroService) { }

  ngOnInit(): void {
    this.cargarMiembros();
  }

  cargarMiembros(): void {
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

  limpiarBusqueda(): void {
    this.searchControl.reset('');
    this.cargarMiembros();
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
    this.cargarMiembros();
  }

  eliminarMiembro(id: number): void {
    this.miembroIdEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean) {
    this.showConfirm = false;

    if (confirmed && this.miembroIdEliminar !== null) {
      this.miembroService.eliminarMiembro(this.miembroIdEliminar).subscribe({
        next: () => this.cargarMiembros(),
        error: (error) => console.log('Error eliminando la miembro ', error),
        complete: () => (this.miembroIdEliminar = null)
      });
    } else {
      this.miembroIdEliminar = null;
    }
  }
}
