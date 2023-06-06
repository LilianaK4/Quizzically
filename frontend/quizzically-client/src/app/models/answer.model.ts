export class Answer {
    id: number;
    content: string;
    correct: boolean;
  
    constructor(id: number, content: string, correct: boolean) {
      this.id = id;
      this.content = content;
      this.correct = correct;
    }
  }
  