package pl.lilianakrol.quizzically.service;

import org.springframework.stereotype.Service;
import pl.lilianakrol.quizzically.models.Answer;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswersService {

    public List<Answer> setAnswersCorrectness(List<Answer> answers, int correctAnswerIndex) {
        List<Answer> setAnswers = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            Answer setAnswer = new Answer();
            setAnswer.setContent(answers.get(i).getContent());
            setAnswer.setCorrect(i == correctAnswerIndex);
            setAnswers.add(setAnswer);
        }
        return setAnswers;
    }
}
