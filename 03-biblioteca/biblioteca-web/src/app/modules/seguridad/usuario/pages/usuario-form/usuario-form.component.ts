import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Usuario } from '../../models/usuario.model';
import { UsuarioService } from '../../services/usuario.service';

import { Rol } from '../../models/rol.model';
import { RolService } from '../../services/rol.service';


@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.css'
})
export class UsuarioFormComponent implements OnInit, OnChanges {

  @Input() usuario: Usuario | null = null;
  
  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();
  
  usuarioForm: FormGroup;
  roles: Rol[] = [];
  
  showPassword = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private rolService: RolService
  ) {
    this.usuarioForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      usuario: ['', Validators.required],
      correo: ['', Validators.required],
      clave: ['', Validators.required],
      rolId: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadRoles();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['usuario'] && this.usuario) {
      this.usuarioForm.patchValue(this.usuario);
    } else {
      this.usuarioForm.reset();
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  private loadRoles(): void {
    this.rolService.obtenerRoles().subscribe({
      next: (data) => {
        console.log("Respuesta del backend:", data);
        this.roles = data
      },
      error: (e) => console.log("Error cargando tipos de usuario: ", e)

    });
  }

  onSubmit(): void {
    if (this.usuarioForm.valid) {
      const formValue = this.usuarioForm.value;
      const id = this.usuario?.id;

      this.usuarioService.guardarUsuario(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando usuario: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
