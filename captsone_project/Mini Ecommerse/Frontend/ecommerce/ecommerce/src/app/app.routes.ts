import { Routes } from '@angular/router';
import { Login } from './auth/login/login';

import { ProductForm } from './product/product-form/product-form';
import { ProductList } from './product/product-list/product-list';
import { Cart } from './order/cart/cart';
import { OrderList } from './order/order-list/order-list';
import { Register } from './auth/register/register';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'products', component: ProductList },
  { path: 'products/new', component: ProductForm },
  { path: 'products/edit/:id', component: ProductForm },
  { path: 'cart', component: Cart },
  { path: 'orders', component: OrderList },
  { path: 'register', component: Register }

];