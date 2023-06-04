import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { SignUpRequestModel } from 'src/app/models/signuUpRequest.model';
import { LoginModel } from 'src/app/models/login.model';
import { AuthenticatedResponse } from 'src/app/models/authenticated-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  private auth: AuthenticatedResponse = {
    authenticationToken: '',
    refreshToken: '',
    expiresAt: new Date(),
    username: ''
  };

  constructor(private httpClient: HttpClient) {
  }


  signup(signUpRequestModel: SignUpRequestModel): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/auth/signup', signUpRequestModel, { responseType: 'text' });
  }

  login(loginRequestPayload: LoginModel): Observable<boolean> {
    return this.httpClient.post<AuthenticatedResponse>('http://localhost:8080/api/auth/login',
      loginRequestPayload).pipe(map(data => {
        this.auth.authenticationToken = data.authenticationToken;
        this.auth.username = data.username;
        this.auth.refreshToken = data.refreshToken;
        this.auth.expiresAt = data.expiresAt;

        this.loggedIn.emit(true);
        this.username.emit(data.username);
        return true;
      }));
  }






}