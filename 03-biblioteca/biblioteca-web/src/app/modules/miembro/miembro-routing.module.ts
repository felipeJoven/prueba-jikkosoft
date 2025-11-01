import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MiembroListarComponent } from './pages/miembro-listar/miembro-listar.component';

const routes: Routes = [
  { path: '', component: MiembroListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MiembroRoutingModule { }
