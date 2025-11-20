import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { LibroRoutingModule } from './libro-routing.module';
import { LibroListComponent } from './pages/libro-list/libro-list.component';
import { LibroFormComponent } from './pages/libro-form/libro-form.component';


@NgModule({
  declarations: [
    LibroListComponent,
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
