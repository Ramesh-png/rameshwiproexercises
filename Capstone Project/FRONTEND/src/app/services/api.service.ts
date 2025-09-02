import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  private authHeaders() {
    return new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  // ================== Auth APIs ==================
  login(credentials: any): Observable<any> {
    // backend should accept { username, password }
    return this.http.post(`${this.base}/login`, credentials);
  }

  register(user: any): Observable<any> {
    // adjust path if your backend expects /users/register
    return this.http.post(`${this.base}/users/register`, user);
  }

  // ================== Product APIs ==================
  products(): Observable<any[]> { 
    return this.http.get<any[]>(`${this.base}/product`); 
  }

  getProduct(id: number): Observable<any> { 
    return this.http.get<any>(`${this.base}/product/${id}`); 
  }

  createProduct(p: any): Observable<any> { 
    return this.http.post(`${this.base}/product`, p, { headers: this.authHeaders() }); 
  }

  updateProduct(p: any): Observable<any> { 
    return this.http.put(`${this.base}/product`, p, { headers: this.authHeaders() }); 
  }

  deleteProduct(id: number): Observable<any> { 
    return this.http.delete(`${this.base}/product/${id}`, { headers: this.authHeaders() }); 
  }

  // ================== Cart APIs ==================
  addToCart(userId: number, productId: number, qty: number): Observable<any> { 
    return this.http.post(
      `${this.base}/cart/addProd?userId=${userId}&productId=${productId}&qty=${qty}`, 
      {}, 
      { headers: this.authHeaders() }
    ); 
  }

  viewCart(userId: number): Observable<any[]> { 
    return this.http.get<any[]>(`${this.base}/cart/${userId}`, { headers: this.authHeaders() }); 
  }

  updateCart(itemId: number, qty: number): Observable<any> { 
    return this.http.put(`${this.base}/cart/update?itemId=${itemId}&qty=${qty}`, {}, { headers: this.authHeaders() }); 
  }

  deleteCart(itemId: number): Observable<any> { 
    return this.http.delete(`${this.base}/cart/deleteProd/${itemId}`, { headers: this.authHeaders() }); 
  }

  // ================== Order APIs ==================
  placeOrder(userId: number, cart: any[]): Observable<any> {
    // cart = [ { productId: 1, quantity: 2 }, { productId: 5, quantity: 1 } ]
    return this.http.post(`${this.base}/order?userId=${userId}`, cart, { headers: this.authHeaders() });
  }

  myOrders(userId: number): Observable<any[]> { 
    return this.http.get<any[]>(`${this.base}/order/${userId}`, { headers: this.authHeaders() }); 
  }
}
