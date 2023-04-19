package pl.lilianakrol.quizzically.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "questions")
public
class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idQuestion;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "aAnswer", columnDefinition = "TEXT")
    private String aAnswer;

    @Column(name = "bAnswer", columnDefinition = "TEXT")
    private String bAnswer;

    @Column(name = "cAnswer", columnDefinition = "TEXT")
    private String cAnswer;

    @Column(name = "dAnswer", columnDefinition = "TEXT")
    private String dAnswer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_quiz", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Quiz idQuiz;

    public Question() {
    }

}
