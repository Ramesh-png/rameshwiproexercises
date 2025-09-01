import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.html'
})
export class Cart {
items: any;
getTotal(): string|number {
throw new Error('Method not implemented.');
}
  userId = 1; // TODO: replace with logged-in user id
  cart: any[] = [];

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.loadCart();
  }

  loadCart() {
    this.cartService.getCart(this.userId).subscribe({
      next: (res) => this.cart = res,
      error: (err) => console.error('❌ Failed to load cart', err)
    });
  }

  

  remove(itemId: number) {
    this.cartService.deleteProduct(itemId).subscribe({
      next: () => this.loadCart(),
      error: (err) => console.error('❌ Failed to remove', err)
    });
  }

  checkout() {
  this.cartService.checkout(this.userId).subscribe({
    next: () => {
      alert('✅ Order placed successfully!');
      this.loadCart();  // clear cart after checkout
    },
    error: (err: any) => {
      console.error('❌ Checkout failed', err);
      alert('Failed to place order!');
    }
  });
}

}
