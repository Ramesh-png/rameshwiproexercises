import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'login',
  template: `
  <div class="login-container">
    <div class="login-card">
      <h2 class="title">Login</h2>
      <form (ngSubmit)="submit()">
        <div class="mb-3">
          <label class="form-label">Username</label>
          <input class="form-control" [(ngModel)]="username" name="username" required>
        </div>
        <div class="mb-3">
          <label class="form-label">Password</label>
          <input type="password" class="form-control" [(ngModel)]="password" name="password" required>
        </div>
        <button class="btn btn-success loginbtn   w-100">Login</button>
      </form>
    </div>
  </div>
  `,
  styles: [`
    .login-container {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      padding: 20px;

   
      background: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSx_THhCHDad_b6MuuFzp5sA2ndDzVkxVcbg&s') no-repeat center center;
      background-size: cover;

    }

    .login-card {
      background: white;
      padding: 30px;
      border-radius: 15%;
      box-shadow: 0 4px 15px rgba(0,0,0,0.15);
      width: 100%;
      max-width: 400px;
    }
    .loginbtn{
      background-color: #000000ff;
    }

    .title {
      font-size: 1.6rem;
      font-weight: bold;
      margin-bottom: 20px;
      text-align: center;
      color: #000000ff;
    }

    .form-label {
      font-weight: 500;
      margin-bottom: 6px;
      display: block;
      color: #374151;
    }

    .form-control {
      padding: 10px;
      border-radius: 6px;
      border: 1px solid #d1d5db;
      font-size: 0.95rem;
      width: 100%;
      transition: border-color 0.3s ease;
    }
    .form-control:focus {
      border-color: #2563eb;
      outline: none;
      box-shadow: 0 0 0 3px rgba(37,99,235,0.2);
    }

    .btn-primary {
      background: #1e3a8a;
      border: none;
      color: white;
      font-weight: 500;
      padding: 10px;
      border-radius: 6px;
      cursor: pointer;
      transition: background 0.3s;
    }
    .btn-primary:hover {
      background: #2563eb;
    }

    @media (max-width: 480px) {
      .login-card { padding: 20px; }
      .title { font-size: 1.3rem; }
    }
  `]
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private auth: AuthService) {}

  submit() {
    this.auth.login(this.username, this.password).subscribe({
      next: r => { 
        this.auth.setSession(r); 
        location.href = '/'; 
      },
      error: _ => alert('Login failed')
    });
  }
}
