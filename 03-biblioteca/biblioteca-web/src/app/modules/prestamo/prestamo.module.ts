import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../../shared/shared.module';

import { PrestamoRoutingModule } from './prestamo-routing.module';
import { PrestamoListComponent } from './pages/prestamo-list/prestamo-list.component';
import { PrestamoFormComponent } from './pages/prestamo-form/prestamo-form.component';


@NgModule({
  declarations: [
    PrestamoListComponent,
    PrestamoFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    PrestamoRoutingModule
  ]
})
export class PrestamoModule { }
