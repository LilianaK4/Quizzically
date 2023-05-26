package pl.lilianakrol.quizzically.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TotalPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int totalScore;


    public TotalPoints() {
    }

    public TotalPoints(Long userId, int totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
    }


}
