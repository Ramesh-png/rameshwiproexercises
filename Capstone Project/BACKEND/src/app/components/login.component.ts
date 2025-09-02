import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({ selector: 'login', template: `
<div class="container" style="max-width:420px;">
  <h3>Login</h3>
  <form (ngSubmit)="submit()">
    <div class="mb-3"><label>Username</label><input class="form-control" [(ngModel)]="username" name="username" required></div>
    <div class="mb-3"><label>Password</label><input type="password" class="form-control" [(ngModel)]="password" name="password" required></div>
    <button class="btn btn-primary">Login</button>
  </form>
  <div class="text-muted mt-2">admin/Admin@123 or user/User@123</div>
</div>` })
export class LoginComponent {
  username='user'; password='User@123';
  constructor(private auth: AuthService){}
  submit(){ this.auth.login(this.username, this.password).subscribe({ next: r=>{ this.auth.setSession(r); location.href='/'; }, error: _=> alert('Login failed') }); }
}
