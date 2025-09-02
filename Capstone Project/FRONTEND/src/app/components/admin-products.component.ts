import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'admin-products',
  template: `
  <div class="admin-container">
    <h2>Admin Page</h2>

    <!-- Product Form -->
    <form #productForm="ngForm" (ngSubmit)="save()" class="form-card">
  <div class="mb-3">
    <input class="form-control" placeholder="Name" [(ngModel)]="form.name" name="name" required #name="ngModel">
    <div *ngIf="name.invalid && name.touched" class="text-danger">Name is required</div>
  </div>

  <div class="mb-3">
    <input class="form-control" placeholder="Description" [(ngModel)]="form.description" name="description" required #description="ngModel">
    <div *ngIf="description.invalid && description.touched" class="text-danger">Description is required</div>
  </div>

  <div class="mb-3">
    <input class="form-control" placeholder="Price" type="number" [(ngModel)]="form.price" name="price" required #price="ngModel">
    <div *ngIf="price.invalid && price.touched" class="text-danger">Price is required</div>
  </div>

  <div class="mb-3">
    <input class="form-control" placeholder="Quantity" type="number" [(ngModel)]="form.quantity" name="quantity" required #quantity="ngModel">
    <div *ngIf="quantity.invalid && quantity.touched" class="text-danger">Quantity is required</div>
  </div>

  <div class="mb-3">
    <input class="form-control" placeholder="Category" [(ngModel)]="form.category" name="category" required #category="ngModel">
    <div *ngIf="category.invalid && category.touched" class="text-danger">Category is required</div>
  </div>

  <div class="mb-3">
    <input class="form-control" placeholder="Image URL" [(ngModel)]="form.image" name="image" required #image="ngModel">
    <div *ngIf="image.invalid && image.touched" class="text-danger">Image URL is required</div>
  </div>

  <div class="btn-group mt-3">
    <button class="btn btn-primary" [disabled]="productForm.invalid">{{ form.id ? 'Update' : 'Create' }}</button>
    <button class="btn btn-secondary" type="button" (click)="reset()">Reset</button>
  </div>
</form>


    <!-- Products Table -->
    <div class="table-wrapper">
      <table class="products-table">
        <thead>
          <tr>
            <th>ID</th><th>Name</th><th>Price</th><th>Qty</th><th>Category</th><th>Image</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let p of products">
            <td>{{p.id}}</td>
            <td>{{p.name}}</td>
            <td>₹ {{p.price | number:'1.2-2'}}</td>
            <td>{{p.quantity}}</td>
            <td>{{p.category}}</td>
            <td><img *ngIf="p.image" [src]="p.image" alt="product image" class="product-img"></td>
            <td>
              <button class="btn btn-sm " (click)="edit(p)">Edit</button>
              <button class="btn btn-sm " (click)="del(p)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  `,
  styles: [`
    .admin-container {
      padding: 20px;
      max-width: 1000px;
      margin: auto;
    }

    .title {
      font-size: 1.8rem;
      font-weight: bold;
      margin-bottom: 20px;
      text-align: center;
      color: #1e3a8a;
    }

    /* Form Styling */
    .form-card {
      background: #f9fafb;
      padding: 20px;
      border-radius: 10px;
      margin-bottom: 20px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    .form-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
      gap: 12px;
      margin-bottom: 15px;
    }

    .form-control {
      padding: 10px;
      border-radius: 6px;
      border: 1px solid #d1d5db;
      font-size: 0.95rem;
      width: 100%;
    }

    .btn-group {
      text-align: center;
      margin-top: 10px;
    }

    .btn {
      padding: 8px 16px;
      border-radius: 6px;
      cursor: pointer;
      font-size: 0.9rem;
      margin-right: 6px;
      border: none;
      transition: all 0.3s ease;
    }

    .btn-primary {
      background: #1e3a8a;
      color: white;
    }
    .btn-primary:hover { background: #1d4ed8; }

    .btn-secondary {
      background: #6b7280;
      color: white;
    }
    .btn-secondary:hover { background: #4b5563; }

    .btn-outline-primary {
      border: 1px solid #1d4ed8;
      color: #1d4ed8;
      background: transparent;
    }
    .btn-outline-primary:hover {
      background: #1d4ed8;
      color: white;
    }

    .btn-outline-danger {
      border: 1px solid #dc2626;
      color: #dc2626;
      background: transparent;
    }
    .btn-outline-danger:hover {
      background: #dc2626;
      color: white;
    }

    /* Table Styling */
    .table-wrapper {
      overflow-x: auto;
    }

    .products-table {
      width: 100%;
      border-collapse: collapse;
      background: #aae9dfff;
    
      overflow: hidden;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    .products-table th, .products-table td {
      padding: 12px 16px;
      border-bottom: 1px solid #e5e7eb;
      text-align: left;
    }

    .products-table th {
      background: #000000ff;
      color: white;
      font-size: 0.9rem;
      text-transform: uppercase;
    }

    .products-table tr:hover {
      background: #f3f4f6;
      transition: 0.3s;
    }

    .product-img {
      width: 50px;
      height: 50px;
      object-fit: cover;
      border-radius: 6px;
      border: 1px solid #e5e7eb;
    }

    /* Responsive */
    @media (max-width: 768px) {
      .title { font-size: 1.5rem; }
      .products-table th, .products-table td { padding: 10px; font-size: 0.85rem; }
      .form-grid { grid-template-columns: 1fr; }
    }
  `]
})
export class AdminProductsComponent implements OnInit {
  products: any[] = [];
  form: any = {};

  constructor(private api: ApiService) {}

  ngOnInit() { this.refresh(); }

  refresh() {
    this.api.products().subscribe(r => this.products = r as any[]);
  }

  save() {
    const call = this.form.id ? this.api.updateProduct(this.form) : this.api.createProduct(this.form);
    call.subscribe(_ => { this.reset(); this.refresh(); });
  }

  edit(p: any) { this.form = { ...p }; }

  del(p: any) {
    if (confirm(`Delete product "${p.name}"?`)) {
      this.api.deleteProduct(p.id).subscribe(_ => this.refresh());
    }
  }

  reset() { this.form = {}; }
}
