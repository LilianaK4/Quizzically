import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  quiz: any;
  currentQuestion: any;
  score: number = 0;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get('/newQuiz').subscribe((response: any) => {
      this.quiz = response;
      this.currentQuestion = this.quiz.questions[0];
    });
  }
}
