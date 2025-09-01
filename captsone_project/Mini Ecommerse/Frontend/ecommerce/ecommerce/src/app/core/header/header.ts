import { Component } from '@angular/core';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-header',
  templateUrl: './header.html'
})
export class Header {
  role: string | null = null;

  constructor(private auth: AuthService) {
    this.role = this.auth.getRole();
  }

  logout() {
    this.auth.logout();
    window.location.href = '/login';
  }
}
