import { Answer } from "./answer.model";

export interface Question {
    id: number;
    content: string;
    answers: Answer[];
    /*
  
    constructor(id: number, content: string, answers: Answer[]) {
      this.id = id;
      this.content = content;
      this.answers = answers;
    }
    */
  }
  