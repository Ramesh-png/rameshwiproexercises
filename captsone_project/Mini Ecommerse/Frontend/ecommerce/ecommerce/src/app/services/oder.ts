import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9191/order';

  constructor(private http: HttpClient, private auth: AuthService) {}

  private getHeaders() {
    return {
      headers: {
        Authorization: 'Bearer ' + (this.auth.getToken() ?? '')
      }
    };
  }

  create(order: any): Observable<any> {
    return this.http.post(this.apiUrl, order, this.getHeaders());
  }

  cancel(orderId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${orderId}`, {}, this.getHeaders());
  }

  getOrders(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${userId}`, this.getHeaders());
  }
}
