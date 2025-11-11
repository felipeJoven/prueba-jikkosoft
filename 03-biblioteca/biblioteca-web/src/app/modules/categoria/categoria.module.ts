import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { CategoriaRoutingModule } from './categoria-routing.module';
import { CategoriaListarComponent } from './pages/categoria-listar/categoria-listar.component';
import { CategoriaFormComponent } from './pages/categoria-form/categoria-form.component';


@NgModule({
  declarations: [
    CategoriaListarComponent,
    CategoriaFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    CategoriaRoutingModule
  ]
})
export class CategoriaModule { }
