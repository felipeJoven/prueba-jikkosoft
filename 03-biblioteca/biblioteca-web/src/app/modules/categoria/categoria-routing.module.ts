import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoriaListarComponent } from './pages/categoria-listar/categoria-listar.component';

const routes: Routes = [
  { path: '', component: CategoriaListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
