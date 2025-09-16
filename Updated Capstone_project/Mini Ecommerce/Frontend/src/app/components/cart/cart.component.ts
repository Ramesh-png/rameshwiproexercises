import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { OrderService } from '../../services/order.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CartItem } from '../../models/cart-item.model';
import { Order, OrderItem } from '../../models/order.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalAmount = 0;
  isLoading = false;

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartItems = this.cartService.getCartItems();
    this.calculateTotal();
  }

  calculateTotal(): void {
    this.totalAmount = this.cartItems.reduce((total, item) => {
      return total + (item.product.price * item.quantity);
    }, 0);
  }

  updateQuantity(item: CartItem, quantity: number): void {
    if (quantity < 1) {
      this.removeFromCart(item);
      return;
    }
    this.cartService.updateQuantity(item.product.id!, quantity);
    this.loadCart();
  }

  removeFromCart(item: CartItem): void {
    this.cartService.removeFromCart(item.product.id!);
    this.loadCart();
  }

  checkout(): void {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    this.isLoading = true;

    const orderItems: OrderItem[] = this.cartItems.map(item => ({
      productId: item.product.id!,
      quantity: item.quantity,
      price: item.product.price,
      productName: item.product.name,
      imageUrl: item.product.imageUrl || ''
    }));

    const order: Order = {
      userId: this.authService.getUserId()!,
      totalAmount: this.totalAmount,
      status: 'PENDING',
      orderItems: orderItems
    };

    this.orderService.createOrder(order).subscribe({
      next: (createdOrder) => {
        this.cartService.clearCart();
        this.isLoading = false;
        alert('Order placed successfully!');
        this.router.navigate(['/orders', createdOrder.id]);
      },
      error: (error) => {
        this.isLoading = false;
        console.error('Error creating order:', error);
        alert('Failed to place order. Please try again.');
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