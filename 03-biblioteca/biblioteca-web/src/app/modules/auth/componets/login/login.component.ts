import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AppStorage } from '../../../../core/storage/app.storage';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  correo: string = '';
  clave: string = '';
  showPassword = false;
  message: string = '';

  constructor(
    private appStorage: AppStorage,
    private authService: AuthService,
    private router: Router
  ) {}

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  submit() {
    if (!this.correo || !this.clave) {
      this.mostrarMensaje('Por favor, complete todos los campos.');
      return;
    }

    this.authService.login({ correo: this.correo, clave: this.clave }).subscribe({
      next: (response: any) => {
        this.appStorage.setToken(response.token);
        this.router.navigate(['/inicio']);
      },
      error: (error) => {
        console.error('Error al iniciar sesión:', error);
        this.mostrarMensaje('Credenciales inválidas. Intente nuevamente.');
      }
    });
  }

  mostrarMensaje(texto: string) {
    this.message = texto;
    setTimeout(() => this.message = '', 4000); 
  }
}
