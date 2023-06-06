package pl.lilianakrol.quizzically.dto;

import lombok.*;
import pl.lilianakrol.quizzically.models.Question;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResponse {
    private Long quizId;
    private List<Question> questions;
}
