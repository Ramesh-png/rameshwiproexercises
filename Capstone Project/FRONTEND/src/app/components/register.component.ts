// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { ApiService } from '../services/api.service';

// @Component({
//   selector: 'register',
//   template: `
//   <div class="register-container">
//     <h2 class="text-center mb-4 fw-bold">Create Account</h2>

//     <form (ngSubmit)="register()" #registerForm="ngForm" class="p-4 shadow-sm bg-white rounded">
//       <!-- Username -->
//       <div class="mb-3">
//         <label class="form-label">Username</label>
//         <input type="text" class="form-control" [(ngModel)]="user.username" name="username" required>
//       </div>

//       <!-- Email -->
//       <div class="mb-3">
//         <label class="form-label">Email</label>
//         <input type="email" class="form-control" [(ngModel)]="user.email" name="email" required>
//       </div>

//       <!-- Password -->
//       <div class="mb-3">
//         <label class="form-label">Password</label>
//         <input type="password" class="form-control" [(ngModel)]="user.password" name="password" required>
//       </div>

   

//       <!-- Address -->
//       <div class="mb-3">
//         <label class="form-label">Address</label>
//         <textarea class="form-control" [(ngModel)]="user.address" name="address"></textarea>
//       </div>

//       <!-- Role -->
//       <div class="mb-3">
//         <label class="form-label">Role</label>
//         <select class="form-select" [(ngModel)]="user.role" name="role" required>
//           <option value="CUSTOMER">Customer</option>
//           <option value="ADMIN">Admin</option>
//         </select>
//       </div>

//       <!-- Submit -->
//       <button type="submit" class="btn btn-primary w-100 fw-semibold" [disabled]="!registerForm.form.valid">
//         Register
//       </button>

//       <p class="mt-3 text-center">
//         Already have an account? <a routerLink="/login">Login</a>
//       </p>
//     </form>
//   </div>
//   `,
//   styles: [`
// .register-container {
//   max-width: 500px;
//   margin: 40px auto;
// }
// form {
//   border-radius: 0.6rem;
// }
//   `]
// })
// export class RegisterComponent {
//   user: any = {
//     username: '',
//     email: '',
//     password: '',
//     role: 'CUSTOMER',
//     name: '',
//     address: ''
//   };

//   constructor(private api: ApiService, private router: Router) {}

//   register() {
//     this.api.register(this.user).subscribe({
//       next: () => {
//         alert('✅ Registration successful! Please login.');
//         this.router.navigate(['/login']);
//       },
//       error: err => {
//         console.error('Registration failed', err);
//         alert('❌ Registration failed!');
//       }
//     });
//   }
// }
