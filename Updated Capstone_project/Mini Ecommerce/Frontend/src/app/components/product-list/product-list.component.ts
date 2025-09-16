import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm = "";
  selectedCategory = "";
  uniqueCategories: string[] = [];
  isLoading = true;
  errorMessage = "";

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.filteredProducts = products;
        this.uniqueCategories = [...new Set(products.map(p => p.category))].sort();
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load products. Please try again.';
        console.error('Error loading products:', error);
        this.isLoading = false;
      }
    });
  }

  searchProducts(): void {
    this.filterProducts();
  }

  filterByCategory(): void {
    this.filterProducts();
  }

  private filterProducts(): void {
    this.filteredProducts = this.products.filter(product =>
      (product.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
       product.description.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
       product.category.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
       product.manufacturer.toLowerCase().includes(this.searchTerm.toLowerCase())) &&
      (!this.selectedCategory || product.category === this.selectedCategory)
    );
  }

  addToCart(product: Product): void {
    if (product.quantity > 0) {
      this.cartService.addToCart(product);
    }
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe({
        next: () => {
          this.products = this.products.filter(p => p.id !== id);
          this.filteredProducts = this.filteredProducts.filter(p => p.id !== id);
        },
        error: (error) => {
          console.error('Error deleting product:', error);
        }
      });
    }
  }

  handleImageError(event: Event): void {
    const imgElement = event.target as HTMLImageElement;
    imgElement.style.display = 'none';
    const placeholder = imgElement.nextElementSibling as HTMLElement;
    if (placeholder) {
      placeholder.style.display = 'flex';
    }
  }
}