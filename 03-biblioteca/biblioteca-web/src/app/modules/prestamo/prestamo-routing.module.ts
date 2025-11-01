import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PrestamoListarComponent } from './pages/prestamo-listar/prestamo-listar.component';

const routes: Routes = [
  { path: '', component: PrestamoListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestamoRoutingModule { }
