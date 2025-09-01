import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../services/oder';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './order-list.html'
})
export class OrderList implements OnInit {
  orders: any[] = [];
  userId = 1; // TODO: replace with logged-in user id

  constructor(private orderService: OrderService) {}

  ngOnInit() {
    this.loadOrders();
  }

  loadOrders() {
    this.orderService.getOrders(this.userId).subscribe(o => this.orders = o);
  }

  cancel(orderId: number) {
    this.orderService.cancel(orderId).subscribe(() => this.loadOrders());
  }
}
