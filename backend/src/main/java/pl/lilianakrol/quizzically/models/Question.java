package pl.lilianakrol.quizzically.models;

import jakarta.persistence.ElementCollection;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ElementCollection
    private List<String> answers;
    private int correctAnswer;

    public Question(String content, List<String> answers, int correctAnswer) {
        this.content = content;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getContent() {
        return content;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
