import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // <-- import FormsModule
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,              // make it standalone
  imports: [FormsModule],         // <-- add FormsModule here
  templateUrl: './login.html'
})
export class Login {
  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login({ email: this.email, password: this.password })
      .subscribe({
        next: (res) => {
          console.log('Login success ✅', res);
          this.router.navigate(['/products']); // redirect after login
        },
        error: (err) => {
          console.error('Login failed ❌', err);
          alert('Invalid credentials!');
        }
      });
  }
}
