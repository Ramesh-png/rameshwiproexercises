import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'cart',
  template: `
<h3 class="text-center my-4 fw-bold">My Cart</h3>

<div class="row g-4 justify-content-center">
  <div class="col-12 col-sm-6 col-md-4 col-lg-3" *ngFor="let it of items; let i = index">
    <div class="card h-100 cart-card shadow-sm p-3">
      <!-- Product Image -->
      <div class="card-img-wrapper mb-2">
        <img [src]="it.product?.image || placeholder" 
             alt="{{it.product?.name}}" 
             (error)="onImgError($event)">
      </div>

      <div class="card-body d-flex flex-column">
        <h6 class="fw-semibold text-truncate mb-1" [title]="it.product?.name">
          {{ it.product?.name }}
        </h6>
        <p class=" fw-bold mb-1">Price: ₹ {{ it.product?.price }}</p>
        <p class=" fw-bold mb-2">
          Total: ₹ {{ (it.product?.price || 0) * it.quantity }}
        </p>

        <!-- Quantity Control -->
        <div class="d-flex align-items-center mb-3">
          <label class="me-2 mb-0">Qty:</label>
          <input type="number" [(ngModel)]="it.quantity" min="1"
                 class="form-control form-control-sm text-center"
                 style="max-width:80px"
                 (change)="updateTotal()">
        </div>

        <div class="mt-auto d-flex justify-content-between">
          <button class="btn  btn-sm w-50 me-1" (click)="openOrderForm(i)"> Buy</button>
          <button class="btn  btn-sm w-50 ms-1" (click)="removeItem(i)"> Remove</button>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Cart Total -->
<div *ngIf="items.length" class=" mt-4">
  <h5 class="fw-bold text-center">Cart Total: ₹ {{ cartTotal }}</h5>
</div>

<!-- Empty Cart -->
<div *ngIf="!items.length" class="text-center my-5">
  <div class="alert  shadow-sm">Your cart is empty</div>
</div>

<!-- Order Popup -->
<div class="modal fade show d-block" tabindex="-1" *ngIf="showForm">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Place Order</h5>
        <button type="button" class="btn-close" (click)="closeForm()"></button>
      </div>
      <div class="modal-body">
        <form #orderForm="ngForm">
          <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" [(ngModel)]="orderDetails.name" name="name" required class="form-control">
          </div>
          <div class="mb-3">
            <label class="form-label">Address</label>
            <textarea [(ngModel)]="orderDetails.address" name="address" required class="form-control"></textarea>
          </div>
          <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" [(ngModel)]="orderDetails.phone" name="phone" required class="form-control">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" (click)="closeForm()">Cancel</button>
        <button class="btn btn-primary" (click)="placeOrder()">Place Order</button>
      </div>
    </div>
  </div>
</div>
<div class="modal-backdrop fade show" *ngIf="showForm"></div>
  `,
  styles: [`
.cart-card {
  border-radius: 0.6rem;
  background-color: #fff;

}

.card-img-wrapper {
  height: 180px;
  background: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 0.5rem;
}
.card-img-wrapper img {
  max-height: 100%;
  max-width: 100%;
  object-fit: contain;
 
}

.modal-backdrop {
  position: fixed;
  top: 0; left: 0;
  width: 100%; height: 100%;
  background: rgba(0,0,0,0.5);
  z-index: 1040;
}
.modal {
  z-index: 1050;
}
  `]
})
export class CartComponent implements OnInit {
  items: any[] = [];
  cartTotal: number = 0;
  placeholder = 'https://via.placeholder.com/150x120?text=No+Image';

  showForm = false;
  orderDetails: any = { name: '', address: '', phone: '' };
  selectedIndex: number | null = null;

  constructor(public auth: AuthService) {}

  ngOnInit() {
    this.loadCart();
  }
  

  loadCart() {
    this.items = JSON.parse(localStorage.getItem('cart') || '[]');
    this.updateTotal();
  }

  saveCart() {
    localStorage.setItem('cart', JSON.stringify(this.items));
    this.updateTotal();
  }

  removeItem(index: number) {
    this.items.splice(index, 1);
    this.saveCart();
  }

  updateTotal() {
    this.cartTotal = this.items.reduce(
      (sum, item) => sum + ((item.product?.price || 0) * item.quantity),
      0
    );
  }

  openOrderForm(index: number) {
    this.selectedIndex = index;
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
    this.orderDetails = { name: '', address: '', phone: '' };
    this.selectedIndex = null;
  }

  placeOrder() {
    if (!this.orderDetails.name || !this.orderDetails.address || !this.orderDetails.phone) {
      alert('Please fill all order details!');
      return;
    }

    // get existing orders
    let orders = JSON.parse(localStorage.getItem('orders') || '[]');

    const orderId = Date.now();
    const orderItems = this.selectedIndex !== null ? [this.items[this.selectedIndex]] : this.items;

    const total = orderItems.reduce((sum, it) => sum + (it.product.price * it.quantity), 0);

    const newOrder = {
      id: orderId,
      status: 'PLACED',
      total: total,
      items: orderItems,
      details: { ...this.orderDetails }
    };

    orders.push(newOrder);
    localStorage.setItem('orders', JSON.stringify(orders));

    // clear from cart
    if (this.selectedIndex !== null) {
      this.items.splice(this.selectedIndex, 1);
    } else {
      this.items = [];
    }
    this.saveCart();

    alert('✅ Order placed successfully!');
    this.closeForm();
  }

  onImgError(event: any) {
    event.target.src = this.placeholder;
  }
}
