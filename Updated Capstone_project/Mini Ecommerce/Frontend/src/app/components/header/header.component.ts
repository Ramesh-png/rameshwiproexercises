import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  cartItemCount = 0;
  private cartSubscription: Subscription;

  constructor(
    public authService: AuthService,
    private router: Router,
    private cartService: CartService
  ) {
    this.cartSubscription = this.cartService.getCartItemCountObservable().subscribe(count => {
      this.cartItemCount = count;
    });
  }

  ngOnInit(): void {
    this.cartItemCount = this.cartService.getCartItemCount();
  }

  ngOnDestroy(): void {
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}