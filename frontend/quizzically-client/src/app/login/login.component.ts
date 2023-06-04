import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginModel } from '../models/login.model';
import { UserService } from '../shared/data-access/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  async ngOnInit() {
    if (this.userService.isUserAuthenticated) {
      await this.router.navigate(['/']);
    }
  }


  onSubmit(): void {
    if (this.loginForm.invalid) return;
    const credentials: LoginModel = {
      username: this.loginForm.controls['username'].value,
      password: this.loginForm.controls['password'].value,
    };
    this.userService.login(credentials).subscribe(loggedIn => {
      (async () => {
        if (loggedIn) await this.router.navigate(['/']);
      })();
    });
  }

  openSignUpPage() {
    this.router.navigate(['/sign-up']);
  }
}
