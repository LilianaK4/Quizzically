import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, throwError } from 'rxjs';
import { SignUpRequestModel } from 'src/app/models/signuUpRequest.model';
import { LoginModel } from 'src/app/models/login.model';
import { AuthenticatedResponse } from 'src/app/models/authenticated-response.model';
import { TokenService } from './token.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RefreshToken } from 'src/app/models/refreshToken.model';
import { UserService } from './user.service';

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


  private authLogout: RefreshToken = {
    refreshToken: '',
    username: ''
  };
  

  constructor(private httpClient: HttpClient, private tokenService: TokenService,
    private jwtHelper: JwtHelperService, private userService: UserService) {
  }


  signup(signUpRequestModel: SignUpRequestModel): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/auth/signup', signUpRequestModel, { responseType: 'text' });
  }

  login(loginRequestPayload: LoginModel): Observable<boolean> {
    return this.httpClient.post<AuthenticatedResponse>('http://localhost:8080/api/auth/login', loginRequestPayload)
      .pipe(
        map(data => {
          this.tokenService.setToken(data);
          this.loggedIn.emit(true);
          this.username.emit(data.username);
          return true;
        })
      );
  }


  logout() {
    this.tokenService.clearToken();
    this.userService.clearUsername();
    this.authLogout.refreshToken = this.auth.refreshToken;
    this.authLogout.username = this.auth.username;
    this.httpClient.post('http://localhost:8080/api/auth/logout', this.authLogout,
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        throwError(error);
      })
    this.auth.authenticationToken = '';
    this.auth.username = '';
    this.auth.refreshToken = '';
    this.auth.expiresAt = new Date();
  }


  getJwtToken() {
    return this.auth.authenticationToken;
  }

  isLoggedIn(): boolean {
    const token = this.tokenService.getToken();
    return !!token && !this.jwtHelper.isTokenExpired(token);
  }

  getUserName() {
    return this.auth.username;
  }


}