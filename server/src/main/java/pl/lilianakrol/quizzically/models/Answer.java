package pl.lilianakrol.quizzically.models;

import javax.persistence.*;

@Entity
@Table(name = "answers")

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private int chosenAnswer;

    public Answer(Long id, User user, Question question, int chosenAnswer) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.chosenAnswer = chosenAnswer;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Question getQuestion() {
        return question;
    }

    public int getChosenAnswer() {
        return chosenAnswer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setChosenAnswer(int chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}
