import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";

import { TipoIdentificacion } from "../models/tipo-identificacion.model";

@Injectable({
  providedIn: 'root'
})
export class TipoIdentificacionService {

  private apiUrl = 'http://localhost:8080/v1/tipo-identificacion';
  
    constructor(private http: HttpClient) { }
  
    obtenerTiposIdentificacion(filtro?: string): Observable<TipoIdentificacion[]> {
      const url = filtro ? `${this.apiUrl}?filtro=${filtro}` : this.apiUrl;
      return this.http.get<TipoIdentificacion[]>(url).pipe(
        catchError((e) => {
          console.error("Error al filtrar TipoIdentificacion: ", e);
          return throwError(() => e);
        })
      );
    }
}