import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LibroListarComponent } from './pages/libro-listar/libro-listar.component';

const routes: Routes = [
  { path: '', component: LibroListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LibroRoutingModule { }
