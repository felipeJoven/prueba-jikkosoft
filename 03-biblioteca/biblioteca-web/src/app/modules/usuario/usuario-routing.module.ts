import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioListarComponent } from './pages/usuario-listar/usuario-listar.component';

const routes: Routes = [
  { path: '', component: UsuarioListarComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
