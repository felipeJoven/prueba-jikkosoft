import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Usuario } from '../../models/usuario.model';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-usuario-listar',
  templateUrl: './usuario-listar.component.html',
  styleUrls: ['./usuario-listar.component.css']
})
export class UsuarioListarComponent implements OnInit {

  usuarios: Usuario[] = [];
  usuariosPaginados: any[] = [];
  usuarioSeleccionado: Usuario | null = null;
  usuarioEliminar: number | null = null;

  isLoading = true;
  errorMessage = '';
  showModal = false;
  showConfirm = false;

  searchControl = new FormControl('');

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.isLoading = true;

    this.usuarioService.obtenerUsuarios().subscribe({
      next: (data) => {
        console.log("Usuarios: ", data);
        this.usuarios = data;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);
      },
      error: (e) => {
        console.log("Error cargando usuarios: ", e);
        this.isLoading = false;
      }
    });

  }

  onPageChange(data: any[]) {
    this.usuariosPaginados = data;
  }

  clearSearch(): void {
    this.searchControl.reset('');
    this.loadBooks();
  }

  openModal(usuario?: Usuario): void {
    this.usuarioSeleccionado = usuario ?? null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.usuarioSeleccionado = null;
  }

  onSave(): void {
    this.closeModal();
    this.loadBooks();
  }

  deleteBooks(id: number): void {
    this.usuarioEliminar = id;
    this.showConfirm = true;
  }

  onConfirmDelete(confirmed: boolean) {
    this.showConfirm = false;
    if (confirmed && this.usuarioEliminar !== null) {
      this.usuarioService.eliminarUsuario(this.usuarioEliminar).subscribe({
        next: () => this.loadBooks(),
        error: (error) => console.log('Error eliminando la usuario ', error),
        complete: () => (this.usuarioEliminar = null)
      });
    } else {
      this.usuarioEliminar = null
    }
  }
}
