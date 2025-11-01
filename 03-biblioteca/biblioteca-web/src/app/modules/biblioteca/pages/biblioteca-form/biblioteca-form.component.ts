import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Biblioteca } from '../../model/biblioteca.model';
import { BibliotecaService } from '../../services/biblioteca.service';

@Component({
  selector: 'app-biblioteca-form',
  templateUrl: './biblioteca-form.component.html',
  styleUrl: './biblioteca-form.component.css'
})
export class BibliotecaFormComponent implements OnChanges {

  @Input() biblioteca: Biblioteca | null = null;
  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  bibliotecaForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private bibliotecaService: BibliotecaService
  ) {
    this.bibliotecaForm = this.fb.group({
      nombre: ['', Validators.required],     
      direccion: ['', Validators.required]
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['biblioteca'] && this.biblioteca) {
      this.bibliotecaForm.patchValue(this.biblioteca);
    } else {
      this.bibliotecaForm.reset();
    }
  }

  onSubmit(): void {
    if (this.bibliotecaForm.valid) {
      const formValue = this.bibliotecaForm.value;
      const id = this.biblioteca?.id;

      this.bibliotecaService.guardarBiblioteca(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando biblioteca: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
