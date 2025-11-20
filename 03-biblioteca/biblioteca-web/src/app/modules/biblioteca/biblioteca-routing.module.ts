import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BibliotecaListComponent } from './pages/biblioteca-list/biblioteca-list.component';

const routes: Routes = [
  { path: '', component: BibliotecaListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BibliotecaRoutingModule { }
