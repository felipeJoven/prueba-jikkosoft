import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PrestamoListComponent } from './pages/prestamo-list/prestamo-list.component';

const routes: Routes = [
  { path: '', component: PrestamoListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestamoRoutingModule { }
