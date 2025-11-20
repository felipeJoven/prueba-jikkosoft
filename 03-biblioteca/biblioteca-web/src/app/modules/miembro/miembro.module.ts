import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { MiembroRoutingModule } from './miembro-routing.module';
import { MiembroListComponent } from './pages/miembro-list/miembro-list.component';
import { MiembroFormComponent } from './pages/miembro-form/miembro-form.component';

@NgModule({
  declarations: [
    MiembroListComponent,
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
