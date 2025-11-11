import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Autor } from '../model/autor.model';

@Injectable({
  providedIn: 'root'
})
export class AutorService {

  private apiUrl = 'http://localhost:8080/v1/autor';

  constructor(private http: HttpClient) { }

  obtenerAutores(filtro?: string): Observable<Autor[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Autor[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar autor: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerAutorPorId(id: number): Observable<Autor> {
    return this.http.get<Autor>(this.apiUrl + `/${id}`);
  }

  guardarAutor(postData: Autor, id?: number) {
    if (!id) {
      return this.http.post<Autor>(this.apiUrl, postData);
    } else {
      return this.http.put<Autor>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarAutor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
