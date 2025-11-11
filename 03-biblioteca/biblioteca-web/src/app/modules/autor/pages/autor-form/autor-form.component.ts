import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Autor } from '../../model/autor.model';
import { AutorService } from '../../services/autor.service';

@Component({
  selector: 'app-autor-form',
  templateUrl: './autor-form.component.html',
  styleUrl: './autor-form.component.css'
})
export class AutorFormComponent implements OnChanges {

  @Input() autor: Autor | null = null;
  @Output() guardado = new EventEmitter<void>();
  @Output() cancelar = new EventEmitter<void>();

  autorForm: FormGroup;

  constructor( private fb: FormBuilder, private autorService: AutorService ) {
    this.autorForm = this.fb.group({
      nombre: ['', Validators.required],     
      nacionalidad: ['', Validators.required]
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['autor'] && this.autor) {
      this.autorForm.patchValue(this.autor);
    } else {
      this.autorForm.reset();
    }
  }

  onSubmit(): void {
    if (this.autorForm.valid) {
      const formValue = this.autorForm.value;
      const id = this.autor?.id;

      this.autorService.guardarAutor(formValue, id).subscribe({
        next: () => this.guardado.emit(),
        error: (e) => console.error("Error guardando autor: ", e)
      });
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
