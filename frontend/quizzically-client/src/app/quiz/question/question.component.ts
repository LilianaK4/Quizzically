import { Component, Input } from '@angular/core';
import { Question } from 'src/app/models/question.model';
import { QuizResponse } from 'src/app/models/quiz.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent {
  @Input() question: any;
  selectedAnswers: any;
  quiz: QuizResponse;

  constructor() {
    this.quiz = {} as QuizResponse; // Inicjalizacja właściwości quiz
    this.question = {} as Question;
    this.selectedAnswers = [];
  }

  onAnswerSelected(answer: any) {
    this.selectedAnswers = answer;
  }

  onNextQuestion() {
    console.log("W QUESTION COMPONENT"  + this.selectedAnswers)
    // Sprawdź, czy wybrana odpowiedź jest poprawna i zwiększ licznik punktów
    if (this.selectedAnswers.correct) {
      // Zwiększ punktację
    }

    // Sprawdź, czy istnieje kolejne pytanie
    const currentIndex = this.quiz.questions.indexOf(this.question);
    if (currentIndex < this.quiz.questions.length - 1) {
      const nextQuestion = this.quiz.questions[currentIndex + 1];
      this.question = nextQuestion;
    } else {
      // Przejdź do SummaryComponent
    }
  }
}