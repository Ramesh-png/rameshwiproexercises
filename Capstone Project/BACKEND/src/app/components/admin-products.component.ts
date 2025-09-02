import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({ selector: 'admin-products', template: `
<h3>Products (Admin)</h3>
<form (ngSubmit)="save()" class="mb-3">
  <input class="form-control mb-1" placeholder="Name" [(ngModel)]="form.name" name="name" required>
  <input class="form-control mb-1" placeholder="Description" [(ngModel)]="form.description" name="description">
  <input class="form-control mb-1" placeholder="Price" type="number" [(ngModel)]="form.price" name="price" required>
  <input class="form-control mb-1" placeholder="Quantity" type="number" [(ngModel)]="form.quantity" name="quantity" required>
  <input class="form-control mb-1" placeholder="Category" [(ngModel)]="form.category" name="category" required>
  <input class="form-control mb-1" placeholder="Image URL" [(ngModel)]="form.image" name="image">
  <button class="btn btn-primary">{{form.id? 'Update':'Create'}}</button>
  <button class="btn btn-secondary ms-2" type="button" (click)="reset()">Reset</button>
</form>
<table class="table">
  <tr><th>ID</th><th>Name</th><th>Price</th><th>Qty</th><th>Category</th><th>Image</th><th></th></tr>
  <tr *ngFor="let p of products">
    <td>{{p.id}}</td>
    <td>{{p.name}}</td>
    <td>{{p.price}}</td>
    <td>{{p.quantity}}</td>
    <td>{{p.category}}</td>
    <td><img *ngIf="p.image" [src]="p.image" alt="product image" width="50"></td>
    <td>
      <button class="btn btn-sm btn-outline-primary" (click)="edit(p)">Edit</button>
      <button class="btn btn-sm btn-outline-danger ms-1" (click)="del(p)">Delete</button>
    </td>
  </tr>
</table>
` })
export class AdminProductsComponent implements OnInit{
  products:any[]=[]; form:any={};
  constructor(private api: ApiService){}
  ngOnInit(){ this.refresh(); }
  refresh(){ this.api.products().subscribe(r=> this.products=r as any[]); }
  save(){ const call = this.form.id? this.api.updateProduct(this.form): this.api.createProduct(this.form); call.subscribe(_=>{ this.reset(); this.refresh(); }); }
  edit(p:any){ this.form = { ...p }; }
  del(p:any){ this.api.deleteProduct(p.id).subscribe(_=> this.refresh()); }
  reset(){ this.form={}; }
}
