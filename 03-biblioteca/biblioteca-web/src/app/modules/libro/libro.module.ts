import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { LibroRoutingModule } from './libro-routing.module';
import { LibroListarComponent } from './pages/libro-listar/libro-listar.component';
import { LibroFormComponent } from './pages/libro-form/libro-form.component';


@NgModule({
  declarations: [
    LibroListarComponent,
    LibroFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    LibroRoutingModule
  ]
})
export class LibroModule { }
