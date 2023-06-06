import { Question } from "./question.model";

export interface QuizResponse {
  quizId: number;
  questions: Question[];
}
