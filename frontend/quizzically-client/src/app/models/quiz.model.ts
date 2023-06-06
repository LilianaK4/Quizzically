import { Question } from "./question.model";

export class Quiz {
    quizId: number;
    questions: Question[];
  
    constructor(quizId: number, questions: Question[]) {
      this.quizId = quizId;
      this.questions = questions;
    }



  }
  