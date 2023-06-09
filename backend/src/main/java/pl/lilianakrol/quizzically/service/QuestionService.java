package pl.lilianakrol.quizzically.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lilianakrol.quizzically.models.Question;
import pl.lilianakrol.quizzically.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private AnswersService answersService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }


    public Optional<Question> getQuestionById(long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
