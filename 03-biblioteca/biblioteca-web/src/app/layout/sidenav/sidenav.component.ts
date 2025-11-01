import { Component, OnInit } from '@angular/core';
import { MenuItem } from './menu/menu.model';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  collapsed = true;
  menuItems: MenuItem[] = [];

  rolUsuario: string = 'administrador';

  ngOnInit(): void {
    this.loadMenu();
  }

  loadMenu(): void {
    if (this.rolUsuario === 'administrador') {
      this.menuItems = [
        { label: 'Inicio', icon: 'fa-solid fa-house', routerLink: '/inicio' },
        { label: 'Libros', icon: 'fa-solid fa-book', routerLink: '/libro' },
        { label: 'Bibliotecas', icon: 'fa-solid fa-building-columns', routerLink: '/biblioteca' },
        { label: 'Prestamos', icon: 'fa-solid fa-arrows-rotate', routerLink: '/prestamo' },
        { label: 'Miembros', icon: 'fa-solid fa-users', routerLink: '/miembro' },
        { label: 'Usuarios', icon: 'fa-solid fa-users-cog', routerLink: '/usuario' },
      ];
    } else {
      this.menuItems = [
        { label: 'Inicio', icon: 'fa-solid fa-house', routerLink: '/inicio' },
        { label: 'Libros', icon: 'fa-solid fa-book', routerLink: '/libro' },
        { label: 'Bibliotecas', icon: 'fa-solid fa-building-columns', routerLink: '/biblioteca' },
        { label: 'Prestamos', icon: 'fa-solid fa-arrows-rotate', routerLink: '/prestamo' },
        { label: 'Miembros', icon: 'fa-solid fa-users', routerLink: '/miembro' }
      ];
    }
  }

  toggleCollapse(forceOpen: boolean = false) {
    if (forceOpen) {
      this.collapsed = false;
    } else {
      this.collapsed = !this.collapsed;
    }
  }
}
