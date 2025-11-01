import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

import { Prestamo } from '../model/prestamo.model';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  private apiUrl = 'http://localhost:8080/v1/prestamo';

  constructor(private http: HttpClient) { }

  obtenerPrestamos(filtro?: string): Observable<Prestamo[]> {
    const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
    return this.http.get<Prestamo[]>(url).pipe(
      catchError((e) => {
        console.error("Error al filtrar prestamo: ", e);
        return throwError(() => e);
      })
    );
  }

  obtenerPrestamoPorId(id: number): Observable<Prestamo> {
    return this.http.get<Prestamo>(this.apiUrl + `/${id}`);
  }

  guardarPrestamo(postData: Prestamo, id?: number) {
    if (!id) {
      return this.http.post<Prestamo>(this.apiUrl, postData);
    } else {
      return this.http.put<Prestamo>(this.apiUrl + `/${id}`, postData);
    }
  }

  eliminarPrestamo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
