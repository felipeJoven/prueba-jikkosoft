import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Usuario } from '../../model/usuario.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from '../../../categoria/model/categoria.model';
import { CategoriaService } from '../../../categoria/service/categoria.service';
import { UsuarioService } from '../../services/usuario.service';

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
    categoria: Categoria[] = [];
  
    constructor(
      private fb: FormBuilder,
      private usuarioService: UsuarioService,
      private categoriaService: CategoriaService
    ) {
      this.usuarioForm = this.fb.group({
        titulo: ['', Validators.required],     
      isbn: ['', Validators.required],
      categoriaId: [0, Validators.required],
      });
    }
  
    ngOnInit(): void {
      this.loadCategories();
    }
  
    ngOnChanges(changes: SimpleChanges): void {
      if (changes['usuario'] && this.usuario) {
        this.usuarioForm.patchValue(this.usuario);
      } else {
        this.usuarioForm.reset();
      }
    }
  
    private loadCategories(): void {
      this.categoriaService.obtenerCategorias().subscribe({
        next: (data) => {
          console.log("Respuesta del backend:", data);
          this.categoria = data
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
