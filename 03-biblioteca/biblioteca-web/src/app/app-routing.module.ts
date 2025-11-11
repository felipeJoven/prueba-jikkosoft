import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '', loadChildren: () => import('./modules/seguridad/auth/auth.module').then(m => m.AuthModule) },
  { path: '',
    component: LayoutComponent,
    children: [
      { path: 'inicio', loadChildren:() => import('./modules/home/home.module').then(m => m.HomeModule) },
      { path: 'libro', loadChildren:() => import('./modules/libro/libro.module').then(m => m.LibroModule) },
      { path: 'biblioteca', loadChildren:() => import('./modules/biblioteca/biblioteca.module').then(m => m.BibliotecaModule) },
      { path: 'prestamo', loadChildren:() => import('./modules/prestamo/prestamo.module').then(m => m.PrestamoModule) },
      { path: 'miembro', loadChildren:() => import('./modules/miembro/miembro.module').then(m => m.MiembroModule) },
      { path: 'autor', loadChildren:() => import('./modules/autor/autor.module').then(m => m.AutorModule) },
      { path: 'categoria', loadChildren:() => import('./modules/categoria/categoria.module').then(m => m.CategoriaModule) },
      { path: 'usuario', loadChildren:() => import('./modules/seguridad/usuario/usuario.module').then(m => m.UsuarioModule) },
    ]
  },
  { path: '**', redirectTo: 'inicio' }   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
