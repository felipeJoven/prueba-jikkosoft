import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorListarComponent } from './pages/autor-listar/autor-listar.component';

const routes: Routes = [
  { path: '', component: AutorListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutorRoutingModule { }
