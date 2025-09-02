import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({ selector: 'orders', template: `
<h3>My Orders</h3>
<table class="table">
  <tr><th>ID</th><th>Status</th><th>Total</th></tr>
  <tr *ngFor="let o of orders"><td>{{o.id}}</td><td>{{o.status}}</td><td>{{o.total | number:'1.2-2'}}</td></tr>
</table>
` })
export class OrdersComponent implements OnInit{
  orders:any[]=[];
  constructor(private api: ApiService, private auth: AuthService){}
  ngOnInit(){ this.api.myOrders(this.auth.userId!).subscribe(r=> this.orders=r as any[]); }
}
