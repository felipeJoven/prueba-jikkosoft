import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { MiembroRoutingModule } from './miembro-routing.module';
import { MiembroListarComponent } from './pages/miembro-listar/miembro-listar.component';
import { MiembroFormComponent } from './pages/miembro-form/miembro-form.component';


@NgModule({
  declarations: [
    MiembroListarComponent,
    MiembroFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    MiembroRoutingModule
  ]
})
export class MiembroModule { }
