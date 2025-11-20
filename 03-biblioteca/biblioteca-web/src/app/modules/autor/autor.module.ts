import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { AutorRoutingModule } from './autor-routing.module';
import { AutorListComponent } from './pages/autor-list/autor-list.component';
import { AutorFormComponent } from './pages/autor-form/autor-form.component';


@NgModule({
  declarations: [
    AutorListComponent,
    AutorFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    AutorRoutingModule
  ]
})
export class AutorModule { }
