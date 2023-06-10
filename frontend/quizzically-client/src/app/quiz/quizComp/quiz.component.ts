import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { QuizResponse } from 'src/app/models/quiz.model';
import { QuizService } from 'src/app/shared/data-access/service/quiz.service';
import { UserService } from 'src/app/shared/data-access/service/user.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SummaryComponent } from '../../summary/summary.component';
import { ActivatedRoute, Router, withHashLocation } from '@angular/router';
import { ScoreUpdateRequest } from 'src/app/models/scoreUpdateRequest.model';
import { LoginComponent } from 'src/app/login/login.component';



@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit {
  quiz: QuizResponse;
  questions: Question[]; // Tablica pytań
  currentQuestion: Question; // Aktualne pytanie
  questionIndex: number; // Indeks aktualnego pytania
  selectedAnswer: Answer; // Wybrana odpowiedź przez użytkownika
  score: number; // Suma punktów
  
 
  constructor(private quizService: QuizService, 
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog) {
    this.quiz = {} as QuizResponse;
    this.questions = [];
    this.currentQuestion = {} as Question;
    this.questionIndex = 0;
    this.selectedAnswer = {} as Answer;
    this.score = 0;
  
  }



ngOnInit() {
  this.quizService.getNewQuiz(this.userService.getUsername() ?? '').subscribe((data: QuizResponse) => {
    this.quiz = data;
    this.questions = data.questions;
    this.questionIndex = 0;


    this.questions.forEach((question: Question) => {

      question.answers = question.answers.map((answer: Answer) => {
        const correct = answer.correct; 
        return { ...answer, correct: correct };
      });
    });

    this.currentQuestion = this.questions[this.questionIndex];
  });
}

goToNextQuestion() {

  const previousAnswer = this.selectedAnswer; 

  if (previousAnswer && previousAnswer.correct) {
    this.score += 10;
  }

  this.selectedAnswer = {} as Answer;

  if (this.questionIndex < this.questions.length - 1) {
    this.questionIndex++;
    this.currentQuestion = this.questions[this.questionIndex];
  } else {
    this.updateScore(this.quiz.quizId, this.score);
    this.getResultsForQuiz(this.quiz.quizId);
  }

}


openSummary() {
    this.router.navigate(['/quiz/summary', this.score]);
}

private getScore() {
  return this.score;
}

getResultsForQuiz(idquiz: number): void{
  this.router.navigate([`/results/${idquiz}/${this.score}`]);
}


selectAnswer(answId: number) {
  this.questions[this.questionIndex].answers.forEach((answ: Answer, i: number) => {

    if (answ.id === answId) {
      this.selectedAnswer = {
        id: answ.id,
        content: answ.content,
        correct: answ.correct
      };
    }
  });
}

private updateScore(quizId: number, score: number) {

  const scoreUpdateRequest = new ScoreUpdateRequest(score); 
  this.quizService.updateQuizScore(quizId, scoreUpdateRequest)

}

navigateToMainMenu(): void {
  this.router.navigate(['/main-menu']);
}


}



