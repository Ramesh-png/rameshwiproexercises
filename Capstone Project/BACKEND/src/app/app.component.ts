import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  template: `
<nav class="navbar navbar-expand-lg navbar-dark gradient-navbar shadow-sm sticky-top">
  <div class="container-fluid">
    <!-- Brand -->
    <a class="navbar-brand fw-bold fs-4" routerLink="/">Mini E‑Com</a>

    <!-- Toggle button for mobile -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar Links -->
    <div class="collapse navbar-collapse" id="navbarContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a routerLink="/" class="nav-link" routerLinkActive="active">Catalog</a>
        </li>
        <li class="nav-item" *ngIf="auth.isLoggedIn()">
          <a routerLink="/cart" class="nav-link position-relative">
            <i class="bi bi-cart-fill me-1"></i> Cart
            <span class="badge bg-danger rounded-pill position-absolute top-0 start-100 translate-middle">
              {{ auth.cartCount || 0 }}
            </span>
          </a>
        </li>
        <li class="nav-item" *ngIf="auth.isLoggedIn()">
          <a routerLink="/orders" class="nav-link">My Orders</a>
        </li>
        <li class="nav-item" *ngIf="auth.role==='ADMIN'">
          <a routerLink="/admin/products" class="nav-link">Products</a>
        </li>
      </ul>

      <!-- Right Side Auth Buttons -->
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item" *ngIf="!auth.isLoggedIn()">
          <a routerLink="/login" class="btn btn-light text-primary px-4 rounded-pill shadow-sm">Login</a>
        </li>
        <li class="nav-item" *ngIf="auth.isLoggedIn()">
          <a href="#" (click)="auth.logout()" class="btn btn-outline-light px-4 rounded-pill shadow-sm ms-2">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Main content -->
<div class="container my-4">
  <router-outlet></router-outlet>
</div>
  `,
  styles: [`
/* Gradient navbar */
.gradient-navbar {
  background: linear-gradient(90deg, #1e3c72, #2a5298);
  color: #fff;
  borerder-bottom: 1px solid rgba(255, 255, 255, 0.1);
}
.gradient-navbar .nav-link {
  color: #fff;
  font-weight: 500;
  transition: all 0.3s ease;
}
.gradient-navbar .nav-link:hover {
  color: #ffd700;
  text-decoration: underline;
}
.gradient-navbar .navbar-brand {
  color: #fff;
  letter-spacing: 1px;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.3);
}
.gradient-navbar .btn {
  font-weight: 500;
  transition: all 0.3s ease;
}
.gradient-navbar .btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.2);
}
.badge {
  font-size: 0.75rem;
  font-weight: 600;
}

/* Responsive adjustments */
@media (max-width: 576px) {
  .navbar-brand { font-size: 1.3rem; }
  .nav-link { font-size: 0.9rem; padding: 0.4rem 0.6rem; }
  .btn { padding: 0.35rem 0.8rem; font-size: 0.85rem; }
}
  `]
})
export class AppComponent {
  constructor(public auth: AuthService) {}
}
