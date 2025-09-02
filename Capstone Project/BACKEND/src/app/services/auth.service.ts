import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {
  token: string | null = localStorage.getItem('token');
  role: string | null = localStorage.getItem('role');
  userId: number | null = Number(localStorage.getItem('userId')) || null;
  base = 'http://localhost:8080';
  constructor(private http: HttpClient){}
  login(username: string, password: string){ return this.http.post<any>(`${this.base}/user/login`, { username, password }); }
  setSession(d:any){ this.token=d.token; this.role=d.role; this.userId=d.userId; localStorage.setItem('token',this.token!); localStorage.setItem('role',this.role!); localStorage.setItem('userId', String(this.userId)); }
  isLoggedIn(){ return !!this.token; }
  logout(){ localStorage.clear(); location.href='/'; }
}
