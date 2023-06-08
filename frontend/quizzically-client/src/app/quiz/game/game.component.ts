import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question.model';
import { QuizResponse } from 'src/app/models/quiz.model';
import { AuthService } from 'src/app/shared/data-access/service/auth.service';
import { QuizService } from 'src/app/shared/data-access/service/quiz.service';
import { UserService } from 'src/app/shared/data-access/service/user.service';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  quiz: QuizResponse;
  currentQuestion: number;
  score: number;
  username: string | null;


  constructor(
    private router: Router,
    private quizService: QuizService,
    private authService: AuthService,
    private userService: UserService,
    private route: ActivatedRoute
  ) { 
    this.quiz = {} as QuizResponse;
    this.currentQuestion = 0;
    this.score = 0;
    this.username = this.userService.getUsername();
 
        
  }


  ngOnInit(): void {
    // Pobranie danych quizu z serwera

    this.quizService.getNewQuiz(this.userService.getUsername()?.toString() ?? '').subscribe(quiz => {
      this.quiz = quiz;
      this.startQuiz();
    });
    this.goToNextQuestion();

  }

  startQuiz(): void {
    // Inicjalizacja stanu gry
    this.score = 0;
    this.currentQuestion = 0; // Ustawiamy na 0, bo indeksowanie tablic zazwyczaj zaczyna się od 0
    this.router.navigate(['question', this.currentQuestion + 1]);
  }

  updateScore(newScore: number): void {
    this.score = newScore;
  }

  goToNextQuestion(): void {
    const totalQuestions = 10;
    if (this.currentQuestion < totalQuestions - 1) {
      this.currentQuestion++;
      this.router.navigate(['question', this.currentQuestion], { relativeTo: this.route.parent });
    } else {
      this.router.navigate(['summary']);
    }
  }
}