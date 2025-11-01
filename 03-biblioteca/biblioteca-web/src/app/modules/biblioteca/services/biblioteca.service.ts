import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Biblioteca } from '../model/biblioteca.model';

@Injectable({
  providedIn: 'root'
})
export class BibliotecaService {

  private apiUrl = 'http://localhost:8080/v1/biblioteca';

  constructor(private http: HttpClient) { }

  obtenerBibliotecas(filtro?: string): Observable<Biblioteca[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Biblioteca[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar biblioteca: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerBibliotecaPorId(id: number): Observable<Biblioteca> {
    return this.http.get<Biblioteca>(this.apiUrl + `/${id}`);
  }

  guardarBiblioteca(postData: Biblioteca, id?: number) {
    if (!id) {
      return this.http.post<Biblioteca>(this.apiUrl, postData);
    } else {
      return this.http.put<Biblioteca>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarBiblioteca(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
