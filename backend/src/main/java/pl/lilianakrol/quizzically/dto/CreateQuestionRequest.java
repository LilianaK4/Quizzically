package pl.lilianakrol.quizzically.dto;

import lombok.Data;
import pl.lilianakrol.quizzically.models.Answer;

import java.util.List;

@Data
public class CreateQuestionRequest {
    private String content;
    private List<Answer> answers;
    private int correctAnswer;
}
