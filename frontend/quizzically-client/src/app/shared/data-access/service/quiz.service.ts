import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { QuizResponse } from 'src/app/models/quiz.model';
import { QuizRequest } from 'src/app/models/quizRequest.model';
import { ScoreUpdateRequest } from 'src/app/models/scoreUpdateRequest.model';


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

  updateQuizScore(quizId: number, scoreUpdateRequest: ScoreUpdateRequest) {
    console.log("scoreUpdateRequest po przekazaniu do funkcji: " + scoreUpdateRequest.score);
    const url = `${this.apiUrl}/${quizId}/score`;
    this.http.patch(url, scoreUpdateRequest).subscribe(
      response => {
        console.log("Score updated successfully");
        // Dodaj tutaj odpowiednie działania po zaktualizowaniu wyniku quizu
      },
      error => {
        console.log("Błąd podczas aktualizacji wyniku quizu:", error);
        // Dodaj tutaj obsługę błędów, jeśli to konieczne
      }
    );
  }
  



}
