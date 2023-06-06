package pl.lilianakrol.quizzically.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultRequest {
    private int totalPoints;
    private Long quizId;
    private Long userId;
}
