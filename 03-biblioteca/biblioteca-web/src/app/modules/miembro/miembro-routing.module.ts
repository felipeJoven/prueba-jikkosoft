import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MiembroListComponent } from './pages/miembro-list/miembro-list.component';

const routes: Routes = [
  { path: '', component: MiembroListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MiembroRoutingModule { }
