package pl.lilianakrol.quizzically.models;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Setter
@Getter
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
}
