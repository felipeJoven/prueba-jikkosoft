import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Categoria } from '../model/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private apiUrl = 'http://localhost:8080/v1/categoria';

  constructor(private http: HttpClient) { }

  obtenerCategorias(filtro?: string): Observable<Categoria[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Categoria[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar categoria: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerCategoriaPorId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(this.apiUrl + `/${id}`);
  }

  guardarCategoria(postData: Categoria, id?: number) {
    if (!id) {
      return this.http.post<Categoria>(this.apiUrl, postData);
    } else {
      return this.http.put<Categoria>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
