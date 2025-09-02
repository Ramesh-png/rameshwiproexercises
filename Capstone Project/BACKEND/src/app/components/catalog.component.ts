import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'catalog',
  template: `
    <h3 class="my-4 text-center fw-bold">Product Catalog</h3>

    <!-- Search & Filter -->
    <div class="row mb-4 g-2 justify-content-center">
      <div class="col-md-5">
        <input type="text" class="form-control shadow-sm" placeholder="Search by name..." [(ngModel)]="searchTerm" (input)="applyFilters()">
      </div>
      <div class="col-md-5">
        <select class="form-select shadow-sm" [(ngModel)]="selectedCategory" (change)="applyFilters()">
          <option value="">All Categories</option>
          <option *ngFor="let cat of categories" [value]="cat">{{ cat }}</option>
        </select>
      </div>
    </div>

    <!-- Product Grid -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
      <div class="col" *ngFor="let p of filteredProducts">
        <div class="card h-100 flipkart-card">
          <!-- Image -->
          <div class="card-img-wrapper">
            <img [src]="p.image || placeholder" alt="{{p.name}}" (error)="onImgError($event)">
          </div>

          <!-- Card Body -->
          <div class="card-body d-flex flex-column">
            <h6 class="product-title text-truncate" [title]="p.name">{{ p.name }}</h6>
            <p class="product-description text-truncate" [title]="p.description">{{ p.description }}</p>
            <p class="product-category text-muted">{{ p.category }}</p>

            <!-- Rating -->
            <div class="rating mb-2">
              <ng-container *ngFor="let star of [1,2,3,4,5]">
                <i class="bi" [ngClass]="star <= p.rating ? 'bi-star-fill' : 'bi-star'"></i>
              </ng-container>
              <span>({{p.rating || 0}})</span>
            </div>

            <!-- Price & Stock -->
            <div class="price-stock mt-auto d-flex justify-content-between align-items-center">
              <span class="fw-bold text-primary">₹ {{ p.price }}</span>
              <span class="badge bg-light text-dark">{{ p.quantity }} in stock</span>
            </div>

            <!-- Add to Cart Button -->
            <button 
              class="btn btn-warning w-100 mt-2 fw-semibold"
              (click)="addToCart(p)"
              [disabled]="!auth.isLoggedIn() || p.quantity === 0"
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .flipkart-card {
      transition: transform 0.3s ease, box-shadow 0.3s ease;
      border-radius: 0.5rem;
      border: none;
      box-shadow: 0 2px 6px rgba(0,0,0,0.1);
      background-color: #fff;
    }
    .flipkart-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 20px rgba(0,0,0,0.2);
    }
    .card-img-wrapper {
      width: 100%;
      height: 200px;
      background: #f5f5f5;
      display: flex;
      align-items: center;
      justify-content: center;
      overflow: hidden;
      border-radius: 0.5rem 0.5rem 0 0;
    }
    .card-img-wrapper img {
      max-height: 100%;
      max-width: 100%;
      object-fit: contain;
      transition: transform 0.3s ease;
    }
    .flipkart-card:hover .card-img-wrapper img {
      transform: scale(1.05);
    }
    .product-title {
      font-size: 0.9rem;
      font-weight: 500;
    }
    .product-description {
      font-size: 0.8rem;
      color: #555;
      margin: 0.2rem 0;
    }
    .product-category {
      font-size: 0.8rem;
      color: #777;
    }

    /* Rating stars */
    .rating {
      display: inline-flex;
      align-items: center;
      background: #fff;
      padding: 2px 6px;
      border-radius: 4px;
      font-size: 0.8rem;
      box-shadow: 0 1px 2px rgba(0,0,0,0.1);
    }
    .rating i {
      color: #FFB400; /* yellow stars */
      font-size: 0.85rem;
      margin-right: 1px;
    }
    .rating span {
      font-size: 0.75rem;
      color: #555;
      margin-left: 4px;
    }

    .price-stock span {
      font-size: 0.85rem;
    }
    .btn-warning {
      color: #212529;
      font-size: 0.85rem;
    }

    @media (max-width: 576px) {
      .card-img-wrapper { height: 150px; }
    }
  `]
})
export class CatalogComponent implements OnInit {
  products: any[] = [];
  filteredProducts: any[] = [];
  categories: string[] = [];
  searchTerm: string = '';
  selectedCategory: string = '';
  placeholder = 'https://via.placeholder.com/200x150?text=No+Image';

  constructor(private api: ApiService, public auth: AuthService) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.api.products().subscribe({
      next: (res) => {
        this.products = res as any[];
        this.filteredProducts = [...this.products];
        this.categories = Array.from(new Set(this.products.map(p => p.category))).filter(c => c);
      },
      error: (err) => console.error('Error loading products', err)
    });
  }

  applyFilters() {
    this.filteredProducts = this.products.filter(p => {
      const matchesName = p.name.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchesCategory = this.selectedCategory ? p.category === this.selectedCategory : true;
      return matchesName && matchesCategory;
    });
  }

  addToCart(p: any) {
    if (!this.auth.userId) {
      alert('Please login first!');
      return;
    }
    this.api.addToCart(this.auth.userId, p.id, 1).subscribe({
      next: () => alert('Added to cart'),
      error: (err) => console.error('Error adding to cart', err)
    });
  }

  onImgError(event: any) {
    event.target.src = this.placeholder;
  }
}
