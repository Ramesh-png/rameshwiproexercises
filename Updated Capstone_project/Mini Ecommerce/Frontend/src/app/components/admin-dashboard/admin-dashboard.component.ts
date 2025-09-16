import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { UserService } from '../../services/user.service';
import { OrderService } from '../../services/order.service';
import { Product } from '../../models/product.model';
import { User } from '../../models/user.model';
import { Order } from '../../models/order.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  activeTab = 'products';
  products: Product[] = [];
  users: User[] = [];
  orders: Order[] = [];
  newProduct: Product = {
    name: '',
    description: '',
    category: '',
    manufacturer: '',
    price: 0,
    quantity: 0,
    uom: '',
    rating: 0,
    imageUrl: ''
  };
  editingProduct: Product | null = null;

  constructor(
    private productService: ProductService,
    private userService: UserService,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.loadUsers();
    this.loadOrders();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (error) => {
        console.error('Error loading products:', error);
      }
    });
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (error) => {
        console.error('Error loading users:', error);
      }
    });
  }

  loadOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (orders) => {
        this.orders = orders;
      },
      error: (error) => {
        console.error('Error loading orders:', error);
      }
    });
  }

  createProduct(): void {
    if (!this.newProduct.name || this.newProduct.price <= 0 || this.newProduct.quantity < 0) {
      alert('Please fill in all required fields with valid values.');
      return;
    }
    this.productService.createProduct(this.newProduct).subscribe({
      next: (product) => {
        this.products.push(product);
        // Reset form with all required fields
        this.newProduct = { 
          name: '', 
          description: '', 
          category: '',
          manufacturer: '',
          price: 0, 
          quantity: 0, 
          uom: '',
          rating: 0,
          imageUrl: '' 
        };
        alert('Product added successfully!');
      },
      error: (error) => {
        console.error('Error creating product:', error);
        alert('Failed to add product. Please try again.');
      }
    });
  }

  editProduct(product: Product): void {
    this.editingProduct = { ...product };
  }

  updateProduct(): void {
    if (this.editingProduct && this.editingProduct.id) {
      if (!this.editingProduct.name || this.editingProduct.price <= 0 || this.editingProduct.quantity < 0) {
        alert('Please fill in all required fields with valid values.');
        return;
      }
      this.productService.updateProduct(this.editingProduct.id, this.editingProduct).subscribe({
        next: (product) => {
          const index = this.products.findIndex(p => p.id === product.id);
          if (index !== -1) {
            this.products[index] = product;
          }
          this.editingProduct = null;
          alert('Product updated successfully!');
        },
        error: (error) => {
          console.error('Error updating product:', error);
          alert('Failed to update product. Please try again.');
        }
      });
    }
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe({
        next: () => {
          this.products = this.products.filter(p => p.id !== id);
          alert('Product deleted successfully!');
        },
        error: (error) => {
          console.error('Error deleting product:', error);
          alert('Failed to delete product. Please try again.');
        }
      });
    }
  }

  updateOrderStatus(order: Order, status: string): void {
    this.orderService.updateOrderStatus(order.id!, status).subscribe({
      next: (updatedOrder) => {
        const index = this.orders.findIndex(o => o.id === updatedOrder.id);
        if (index !== -1) {
          this.orders[index] = updatedOrder;
        }
        alert('Order status updated successfully!');
      },
      error: (error) => {
        console.error('Error updating order status:', error);
        alert('Failed to update order status. Please try again.');
      }
    });
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