import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BibliotecaListarComponent } from './pages/biblioteca-listar/biblioteca-listar.component';

const routes: Routes = [
  { path: '', component: BibliotecaListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BibliotecaRoutingModule { }
