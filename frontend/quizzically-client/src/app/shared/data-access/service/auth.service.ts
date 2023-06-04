import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpRequestModel } from 'src/app/models/signuUpRequest.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();



  constructor(private httpClient: HttpClient) {
  }



  signup(signUpRequestModel: SignUpRequestModel): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/auth/signup', signUpRequestModel, { responseType: 'text' });
  }







}