import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Libro } from '../model/libro.model';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = 'http://localhost:8080/v1/libro';

  constructor(private http: HttpClient) { }

  obtenerLibros(filtro?: string): Observable<Libro[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Libro[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar libro: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerLibroPorId(id: number): Observable<Libro> {
    return this.http.get<Libro>(this.apiUrl + `/${id}`);
  }

  guardarLibro(postData: Libro, id?: number) {
    if (!id) {
      return this.http.post<Libro>(this.apiUrl, postData);
    } else {
      return this.http.put<Libro>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarLibro(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
