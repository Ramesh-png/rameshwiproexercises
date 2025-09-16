import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OrderService } from '../../services/order.service';
import { AuthService } from '../../services/auth.service';
import { Order } from '../../models/order.model';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(
    private orderService: OrderService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    if (this.authService.isAdmin()) {
      this.orderService.getOrders().subscribe({
        next: (orders) => {
          this.orders = orders;
          this.isLoading = false;
        },
        error: (error) => {
          this.errorMessage = 'Failed to load orders. Ensure you are an admin and retry.';
          console.error('Error loading orders:', error);
          this.isLoading = false;
        }
      });
    } else {
      const userId = this.authService.getUserId();
      if (userId) {
        this.orderService.getOrdersByUserId(userId).subscribe({
          next: (orders) => {
            this.orders = orders;
            this.isLoading = false;
          },
          error: (error) => {
            this.errorMessage = `Failed to load your orders. Error: ${error.statusText} (Status: ${error.status}). Please ensure you are logged in and have access.`;
            console.error('Error loading user orders for userId', userId, ':', error);
            this.isLoading = false;
          }
        });
      } else {
        this.errorMessage = 'User ID not found. Please log in.';
        this.isLoading = false;
      }
    }
  }

  cancelOrder(order: Order): void {
    if (confirm('Are you sure you want to cancel this order?')) {
      this.orderService.cancelOrder(order.id!).subscribe({
        next: () => {
          order.status = 'CANCELLED';
        },
        error: (error) => {
          console.error('Error cancelling order:', error);
          alert('Failed to cancel order. Please try again.');
        }
      });
    }
  }
}