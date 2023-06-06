import { Component, Input } from '@angular/core';
import { Question } from 'src/app/models/question.model';
import { Quiz } from 'src/app/models/quiz.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent {
  @Input() question: any;
  selectedAnswers: any;
  quiz: Quiz;

  constructor() {
    this.quiz = new Quiz(0, []); // Inicjalizacja właściwości quiz
    this.question = new Question(0, '', []);
    this.selectedAnswers = [];
  }

  onAnswerSelected(answer: any) {
    this.selectedAnswers = answer;
  }

  onNextQuestion() {
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