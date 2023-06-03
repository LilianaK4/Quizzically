import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string | undefined;
  password: string | undefined;

  onSubmit() {
    // Obsługa logiki logowania
  }
}

