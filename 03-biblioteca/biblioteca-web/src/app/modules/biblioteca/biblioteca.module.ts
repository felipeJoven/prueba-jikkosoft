import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { BibliotecaRoutingModule } from './biblioteca-routing.module';
import { BibliotecaListComponent } from './pages/biblioteca-list/biblioteca-list.component';
import { BibliotecaFormComponent } from './pages/biblioteca-form/biblioteca-form.component';


@NgModule({
  declarations: [
    BibliotecaListComponent,
    BibliotecaFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    BibliotecaRoutingModule
  ]
})
export class BibliotecaModule { }
