import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { User } from '../../../models/user.model';
import { TokenService } from './token.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginModel } from '../../../models/login.model';
import { AuthenticatedResponse } from '../../../models/authenticated-response.model';
import { HttpClient } from '@angular/common/http';
import { SignUpModel } from 'src/app/models/signUp.model';
import { JwtModule } from '@auth0/angular-jwt';


@Injectable({
    providedIn: 'root',
  })
  export class UserService {
   
   
    private user = new BehaviorSubject<User | null>(null);
    public user$ = this.user.asObservable();

  
    constructor(
      private http: HttpClient,
      private tokenService: TokenService,
      private jwtHelper: JwtHelperService,
    ) {
      const token = this.tokenService.getToken();
      if (!!token && !this.jwtHelper.isTokenExpired(token))
        this.setUser({ authenticationToken: token } as AuthenticatedResponse);
      else this.tokenService.clearToken();

      
    }

  
   
  
    public get isUserAuthenticated(): boolean {
      const token = this.tokenService.getToken();
      if (!!token && !this.jwtHelper.isTokenExpired(token)) return true;
      return false;
    }


    

    public login(loginModel: LoginModel): Observable<boolean> {
      const resp = this.http
        .post<AuthenticatedResponse>(`http://localhost:8080/api/auth/login`, loginModel)
        .pipe(
          map((res: AuthenticatedResponse) => {
            if (!res) return false;
            this.tokenService.setToken(res);
            this.setUser(res);
            localStorage.setItem('username', res.username);
            return true;
          })
        );
    
        return resp;
    }
  
    public logout(): void {
      this.tokenService.clearToken();
      this.clearUser();
    }
  
    private setUser(auth: AuthenticatedResponse | null): void {
      if (!auth) return;
      const decodeToken = this.jwtHelper.decodeToken(auth.authenticationToken);
      const user: User = {
        id: decodeToken['id'],
        username : auth.username,
      };
      this.user.next(user);
    }


    public getUsername(): string | null {
      return localStorage.getItem('username');
    }
  

  
    private clearUser() {
      this.user.next(null);
    }

  

    public clearUsername(): void {
      localStorage.removeItem('username');
    }
  }
  