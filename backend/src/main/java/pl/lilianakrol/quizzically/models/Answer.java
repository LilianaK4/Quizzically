package pl.lilianakrol.quizzically.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private boolean correct;
}
