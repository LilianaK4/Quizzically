package pl.lilianakrol.quizzically.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "quizes")
public
class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idQuiz;

    @Lob
    @Column(name = "quizTitle", nullable = false)
    private String quizTitle;

    @Lob
    @Column(name = "quizDescription", nullable = false, columnDefinition = "TEXT")
    private String quizDescription;

    @Lob
    @Column(name = "quizIcon", nullable = true)
    private byte[] quizIcon;

    @Column(name = "PIN", nullable = true)
    private int pin;

    @Column(name = "sumOfPoints")
    private long sumOfPoints;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User idUser;

    public Quiz() {
    }

}