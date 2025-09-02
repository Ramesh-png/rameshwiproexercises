import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'catalog',
  template: `
    <!-- <h3 class="my-4 text-center fw-bold">PRODUCTS</h3> -->

 

    <!-- Product Grid -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
      <div class="col" *ngFor="let p of filteredProducts">
        <div class="card h-100 flipkart-card">
          <!-- Image -->
          <div class="card-img-wrapper">
            <img [src]="p.image || placeholder" alt="{{p.name}}" (error)="onImgError($event)">
          </div>

          <!-- Card Body -->
          <div class="card-body d-flex flex-column text-center">
            <h6 class="product-title text-truncate title" [title]="p.name">{{ p.name }}</h6>
            <p class="product-description text-truncate" [title]="p.description">{{ p.description }}</p>
            <p class="product-category text-muted">{{ p.category }}</p>

            <!-- Price & Stock -->
            <div class="price-stock mt-auto d-flex justify-content-between align-items-center">
              <span class="fw-bold text-dark">₹ {{ p.price }}</span>
             
            </div>
 <div class="badge text-start  bg-light text-dark">{{ p.quantity }} in stock</div>
            <!-- Add to Cart Button -->
            <button 
              class="btn btn-info  w-100 mt-2 fw-semibold"
              (click)="addToCart(p)"
              [disabled]="!auth.isLoggedIn() || p.quantity === 0"
            >
              Add  Cart
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
 .title{
  background-color: #8ec1eeff;
  border-radius: 12px;
  padding: 2px;
  color: #000000ff;
  font-weight: bold;
  margin-left: 10px;
  margin-right: 10px;

 }
 .card-img-wrapper {
  width: 100%;
  height: 200px;
 
  border:20px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
 
  box-shadow: inset 0 2px 6px rgba(0,0,0,0.05);
}
    .card-img-wrapper img {
      max-height: 100%;
      max-width: 100%;
      object-fit: contain;
     
    }

    .product-title { font-size: 0.9rem; font-weight: 500; }
    .product-description { font-size: 0.8rem; color: #555; margin: 0.2rem 0; }
    .product-category { font-size: 0.8rem; color: #777; }
    .price-stock span { font-size: 0.85rem; }
    .btn-warning { color: #212529; font-size: 0.85rem; }
    @media (max-width: 576px) { .card-img-wrapper { height: 150px; } }
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

    let cart = JSON.parse(localStorage.getItem('cart') || '[]');
    cart.push({ product: p, quantity: 1 });
    localStorage.setItem('cart', JSON.stringify(cart));

    this.auth.cartCount = cart.length;  

    alert('Added to cart');
  }

  onImgError(event: any) {
    event.target.src = this.placeholder;
  }
}
