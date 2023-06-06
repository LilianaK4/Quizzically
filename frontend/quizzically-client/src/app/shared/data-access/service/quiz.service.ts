import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { QuizResponse } from 'src/app/models/quiz.model';
import { QuizRequest } from 'src/app/models/quizRequest.model';


@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/api/quiz'; 

  constructor(private http: HttpClient) {}
  

public getNewQuiz(username: string): Observable<QuizResponse> {
    const url = `${this.apiUrl}/newQuiz`;
    const quizRequest: QuizRequest = { username };
    return this.http.post<QuizResponse>(url, quizRequest)
      .pipe(map((res: QuizResponse) => res));
  }
  



}
