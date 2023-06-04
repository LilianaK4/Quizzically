import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string | undefined;
  password: string | undefined;

  constructor(private router: Router) {}

  onSubmit() {
    // Obsługa logiki logowania
  }

  openSignUpPage() {
    this.router.navigate(['/sign-up']);
  }
}
