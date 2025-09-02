import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ApiService {
  base = 'http://localhost:8080';
  constructor(private http: HttpClient){}
  products(){ return this.http.get<any[]>(`${this.base}/product`); }
  createProduct(p:any){ return this.http.post(`${this.base}/product`, p); }
  updateProduct(p:any){ return this.http.put(`${this.base}/product`, p); }
  deleteProduct(id:number){ return this.http.delete(`${this.base}/product/${id}`); }
  addToCart(userId:number, productId:number, qty:number){ return this.http.post(`${this.base}/cart/addProd?userId=${userId}&productId=${productId}&qty=${qty}`, {}); }
  viewCart(userId:number){ return this.http.get<any[]>(`${this.base}/cart/${userId}`); }
  updateCart(itemId:number, qty:number){ return this.http.put(`${this.base}/cart/update?itemId=${itemId}&qty=${qty}`, {}); }
  deleteCart(itemId:number){ return this.http.delete(`${this.base}/cart/deleteProd/${itemId}`); }
  createOrder(userId:number){ return this.http.post(`${this.base}/order?userId=${userId}`, {}); }
  myOrders(userId:number){ return this.http.get<any[]>(`${this.base}/order/${userId}`); }
}
