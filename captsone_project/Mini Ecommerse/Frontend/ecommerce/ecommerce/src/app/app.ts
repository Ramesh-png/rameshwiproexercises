import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { AuthService } from './services/auth';
import { Header } from "./core/header/header";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Header],
  templateUrl: './app.html',  
  styleUrls: ['./app.css']     // ✅ also ./app.css
})
export class App {
  protected readonly title = signal('ecommerce');
  constructor(public auth: AuthService) {}
  isAdmin(): boolean {
    const token = this.auth.getToken();
    if (!token) return false;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role === 'ADMIN';
    } catch (e) {
      return false;
    }
    }

}
