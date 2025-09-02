import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login.component';
import { CatalogComponent } from './components/catalog.component';
import { CartComponent } from './components/cart.component';
import { OrdersComponent } from './components/orders.component';
import { AdminProductsComponent } from './components/admin-products.component';
import { AuthInterceptor } from './services/auth.interceptor';

const routes: Routes = [
  { path: '', component: CatalogComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cart', component: CartComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'admin/products', component: AdminProductsComponent }
];

@NgModule({
  declarations: [AppComponent, LoginComponent, CatalogComponent, CartComponent, OrdersComponent, AdminProductsComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(routes)],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {}
