import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { QuizResponse } from 'src/app/models/quiz.model';
import { QuizService } from 'src/app/shared/data-access/service/quiz.service';
import { UserService } from 'src/app/shared/data-access/service/user.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SummaryComponent } from '../../summary-dialog/summary.component';
import { ActivatedRoute, Router } from '@angular/router';



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

  //summaryDialogRef?: MatDialogRef<SummaryComponent>;

  

 
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
    //this.score = 0;
    this.quizService.getNewQuiz(this.userService.getUsername() ?? '').subscribe((data: QuizResponse) => {
      this.quiz = data;
      this.questions = data.questions;
      this.questionIndex = 0;
      this.currentQuestion = this.questions[this.questionIndex];
    });
  }
  
  /*

  goToNextQuestion() {
    if (this.selectedAnswer && this.selectedAnswer.isCorrect) {
      this.score += 10;
    }

    this.selectedAnswer = {} as Answer;

    //this.questionIndex++;
    if (this.questionIndex < this.questions.length-1) {
      this.questionIndex++;
      this.currentQuestion = this.questions[this.questionIndex];
    } else {
      this.openSummaryDialog();
    }
  }

*/

goToNextQuestion() {

  const previousAnswer = this.selectedAnswer; // Poprzednia odpowiedź

  console.log("POPRAWNOSCI: " + this.selectedAnswer.isCorrect)
  if (previousAnswer && previousAnswer.isCorrect) {
    this.score += 10;
  }

  this.selectedAnswer = {} as Answer;

  if (this.questionIndex < this.questions.length - 1) {
    this.questionIndex++;
    this.currentQuestion = this.questions[this.questionIndex];
  } else {
    this.getResultsForQuiz(this.quiz.quizId);
  }

  console.log('POINTS: ' + this.score);
}


openSummary() {
  console.log("TO SIE WYWOLUJE OPENSUMMARYDIALOG")
   //this.router.navigateByUrl('/quiz/summary', { state: { score: this.score } });
    this.router.navigate(['/quiz/summary', this.score]);
}

private getScore() {
  return this.score;
}

getResultsForQuiz(idquiz: number): void{
  this.router.navigate([`/results/${idquiz}/${this.score}`]);
}


  selectAnswer(answId: number) {
    this.currentQuestion.answers.forEach((answ: Answer, i: number) => {
      if(answ.id == answId) {
        this.selectedAnswer = answ;
      }
    });
  }

  


}



