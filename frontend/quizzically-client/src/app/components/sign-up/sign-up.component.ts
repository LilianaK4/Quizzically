import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpModel } from 'src/app/models/signUp.model';
import { UserService } from 'src/app/shared/data-access/service/user.service';
import { SignUpRequestModel } from '../../models/signuUpRequest.model';
import { AuthService } from '../../shared/data-access/service/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  signUpModel: SignUpModel;
  signUpRequestModel: SignUpRequestModel;
  signupForm: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.signupForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });

    this.signUpModel = {
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };

    this.signUpRequestModel = {
      name: '',
      surname: '',
      username: '',
      email: '',
      password: ''
    };
  }

  ngOnInit() {}

  signup() {
    if (this.signupForm.valid) {
      this.signUpModel.firstName = this.signupForm.get('name')?.value;
      this.signUpModel.lastName = this.signupForm.get('surname')?.value;
      this.signUpModel.email = this.signupForm.get('email')?.value;
      this.signUpModel.username = this.signupForm.get('username')?.value;
      this.signUpModel.password = this.signupForm.get('password')?.value;
      this.signUpModel.confirmPassword = this.signupForm.get('confirmPassword')?.value;
      this.signUpRequestModel.name = this.signUpModel.firstName;
      this.signUpRequestModel.surname = this.signUpModel.lastName;
      this.signUpRequestModel.email = this.signUpModel.email;
      this.signUpRequestModel.username = this.signUpModel.username;
      this.signUpRequestModel.password = this.signUpModel.password;
  
      this.authService.signup(this.signUpRequestModel).subscribe(
        response => {
          console.log(response); // wyświetl odpowiedź w konsoli
          if (response === 'User Registration Successful') {
            this.router.navigate(['/login'], {
              queryParams: { registered: 'true' }
            });
          } else {
            // Obsłuż inne przypadki odpowiedzi
            console.error('Nieznana odpowiedź:', response);
          }
        },
        error => {
          console.error('Wystąpił błąd podczas rejestracji:', error);
        }
      );
    }
  }

  openLoginPage() {
    this.router.navigate(['/login']);
  }
  

}
