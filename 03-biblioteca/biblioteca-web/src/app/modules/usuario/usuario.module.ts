import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { FormsModule } from '@angular/forms';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioListarComponent } from './pages/usuario-listar/usuario-listar.component';
import { UsuarioFormComponent } from './pages/usuario-form/usuario-form.component';


@NgModule({
  declarations: [
    UsuarioListarComponent,
    UsuarioFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    UsuarioRoutingModule
  ]
})
export class UsuarioModule { }
