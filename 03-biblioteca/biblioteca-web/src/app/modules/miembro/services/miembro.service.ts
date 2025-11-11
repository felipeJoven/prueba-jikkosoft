import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Miembro } from '../models/miembro.model';

@Injectable({
  providedIn: 'root'
})
export class MiembroService {

  private apiUrl = 'http://localhost:8080/v1/miembro';

  constructor(private http: HttpClient) { }

  obtenerMiembros(filtro?: string): Observable<Miembro[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Miembro[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar miembro: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerMiembroPorId(id: number): Observable<Miembro> {
    return this.http.get<Miembro>(this.apiUrl + `/${id}`);
  }

  guardarMiembro(postData: Miembro, id?: number) {
    if (!id) {
      return this.http.post<Miembro>(this.apiUrl, postData);
    } else {
      return this.http.put<Miembro>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarMiembro(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
