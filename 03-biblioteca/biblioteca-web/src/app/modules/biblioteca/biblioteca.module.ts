import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { FormsModule } from '@angular/forms';

import { BibliotecaRoutingModule } from './biblioteca-routing.module';
import { BibliotecaListarComponent } from './pages/biblioteca-listar/biblioteca-listar.component';
import { BibliotecaFormComponent } from './pages/biblioteca-form/biblioteca-form.component';


@NgModule({
  declarations: [
    BibliotecaListarComponent,
    BibliotecaFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    BibliotecaRoutingModule
  ]
})
export class BibliotecaModule { }
