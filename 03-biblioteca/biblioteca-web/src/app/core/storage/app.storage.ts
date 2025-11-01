import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppStorage {
  
  private token$ = new BehaviorSubject<string | null>(localStorage.getItem("token"));

  setToken(token: string) {
    this.token$.next(token);
    localStorage.setItem("token", token);
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }

  setUsuario(usuario: string) {
    localStorage.setItem("usuario", usuario);
  }

  getUsuario(): string | null {
    return localStorage.getItem("usuario");
  }

  setRol(rol: string) {
    localStorage.setItem("rol", rol);
  }

  getRol(): string | null {
    return localStorage.getItem("rol");
  }

  clear() {
    localStorage.clear();
    this.token$.next(null);
  }

  logout() {
    this.clear();
    return true;
  }
}
