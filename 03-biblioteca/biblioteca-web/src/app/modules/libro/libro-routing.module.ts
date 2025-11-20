import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LibroListComponent } from './pages/libro-list/libro-list.component';

const routes: Routes = [
  { path: '', component: LibroListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LibroRoutingModule { }
