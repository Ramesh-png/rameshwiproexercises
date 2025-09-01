import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
 checkout(userId: number) {
  return this.http.post(`${this.apiUrl}/cart/checkout/${userId}`, {});
}

  addToCart(cartItem: { userId: number; productId: any; quantity: number; }) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:9191/cart';

  constructor(private http: HttpClient) {}

  getCart(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${userId}`);
  }

  addProduct(userId: number, productId: number, qty: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/addProd`, { userId, productId, qty });
  }

  updateProduct(userId: number, productId: number, qty: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, { userId, productId, qty });
  }

  deleteProduct(itemId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deleteProd/${itemId}`);
  }
}
