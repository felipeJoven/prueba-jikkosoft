import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

import { AppStorage } from '../../../core/storage/app.storage';
import { Credentials } from '../models/login.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/v1/security';
  
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  
  isLoggedIn$ = this.isLoggedInSubject.asObservable();

  constructor(private http: HttpClient, private appStorage: AppStorage) {
    const token = this.appStorage.getToken();
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        if (decoded.exp * 1000 < Date.now()) {
          this.appStorage.logout();
        } else {
          this.isLoggedInSubject.next(true);
        }
      } catch {
        this.appStorage.logout();
      }
    }
  }

  login(credential: Credentials) {
    return this.http.post(`${this.apiUrl}/login`, credential);
  }

  isAuthenticated(): boolean {
    const token = this.appStorage.getToken();
    if (!token) return false;
    try {
      const decoded: any = jwtDecode(token);
      return decoded.exp * 1000 > Date.now();
    } catch {
      return false;
    }
  }

  logout() {
    return !!this.appStorage.logout();
  }
}
