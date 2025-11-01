import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Usuario } from '../model/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/v1/usuario';
  
    constructor(private http: HttpClient) { }
  
    obtenerUsuarios(filtro?: string): Observable<Usuario[]> {
      const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
      return this.http.get<Usuario[]>(url).pipe(
        catchError((e) => {
          console.error("Error al filtrar usuario: ", e);
          return throwError(() => e);
        })
      );
    }
  
    obtenerUsuarioPorId(id: number): Observable<Usuario> {
      return this.http.get<Usuario>(this.apiUrl + `/${id}`);
    }
  
    guardarUsuario(postData: Usuario, id?: number) {
      if (!id) {
        return this.http.post<Usuario>(this.apiUrl, postData);
      } else {
        return this.http.put<Usuario>(this.apiUrl + `/${id}`, postData);
      }
    }
  
    eliminarUsuario(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
