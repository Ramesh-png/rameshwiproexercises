import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product';
import { CartService } from '../../services/cart';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.html'
})
export class ProductList {
  products: Product[] = [];
  userId = 1; // TODO: replace with logged-in user id

  constructor(private productService: ProductService, private cartService: CartService) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getAll().subscribe({
      next: (res) => this.products = res,
      error: (err) => console.error('❌ Failed to load products', err)
    });
  }

  addToCart(productId: number) {
    this.cartService.addProduct(this.userId, productId, 1).subscribe({
      next: () => alert('✅ Added to cart'),
      error: (err) => console.error('❌ Failed to add to cart', err)
    });
  }
}
