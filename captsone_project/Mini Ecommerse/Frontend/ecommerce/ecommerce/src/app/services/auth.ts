import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9191/user';  // API Gateway URL

  constructor(private http: HttpClient) {}

  // Register new user
  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  // ✅ Login and decode role from JWT
  login(credentials: { email: string; password: string }) {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem('authToken', response.token);

          // Decode token to extract role
          const decoded: any = jwtDecode(response.token);
          if (decoded.role) {
            localStorage.setItem('role', decoded.role);
          }
        })
      );
  }

  // Get token
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Get role
  getRole(): string | null {
    return localStorage.getItem('role');
  }

  // Clear token and role
  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('role');
  }
}
