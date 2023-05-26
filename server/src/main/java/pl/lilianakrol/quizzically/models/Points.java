package pl.lilianakrol.quizzically.models;

import jakarta.persistence.ElementCollection;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Quiz quiz;

    @ElementCollection
    private List<Integer> questionScores;



    public Points() {
    }

    public Points(Quiz quiz, List<Integer> questionScores) {
        this.quiz = quiz;
        this.questionScores = questionScores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Integer> getQuestionScores() {
        return questionScores;
    }

    public void setQuestionScores(List<Integer> questionScores) {
        this.questionScores = questionScores;
    }

    public Optional<Integer> getQuestionScore(int questionIndex) {
        List<Integer> scores = getQuestionScores();
        if (questionIndex >= 0 && questionIndex < scores.size()) {
            return Optional.of(scores.get(questionIndex));
        }
        return Optional.empty(); //when index out of range
    }

}
