import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";

import { Rol } from "../../usuario/models/rol.model";

@Injectable({
  providedIn: 'root'
})
export class RolService {

  private apiUrl = 'http://localhost:8080/v1/rol';
  
    constructor(private http: HttpClient) { }
  
    obtenerRoles(filtro?: string): Observable<Rol[]> {
      const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
      return this.http.get<Rol[]>(url).pipe(
        catchError((e) => {
          console.error("Error al filtrar Rol: ", e);
          return throwError(() => e);
        })
      );
    }
}