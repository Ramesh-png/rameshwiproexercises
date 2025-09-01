import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html'
})
export class Register {
  user = {
    username: '',
    email: '',
    password: ''
  };

  constructor(private auth: AuthService, private router: Router) {}

  onSubmit() {
    this.auth.register(this.user).subscribe({
      next: (res) => {
        console.log('✅ Registration successful', res);
        alert('Registered successfully! Please login.');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('❌ Registration failed', err);
        alert('Registration failed, try again.');
      }
    });
  }
}
