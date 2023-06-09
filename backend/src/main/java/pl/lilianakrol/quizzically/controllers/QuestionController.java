package pl.lilianakrol.quizzically.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.lilianakrol.quizzically.exceptions.QuizzicallyException;
import pl.lilianakrol.quizzically.models.Question;
import pl.lilianakrol.quizzically.models.Quiz;
import pl.lilianakrol.quizzically.repositories.QuestionRepository;
import pl.lilianakrol.quizzically.repositories.QuizRepository;
import pl.lilianakrol.quizzically.service.QuestionService;

import java.util.List;


@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionController {

    QuestionService questionService;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    @PostMapping()
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    //TODO: Create endpoint logic
     @PutMapping("/{idQuestion}")
     Question updateQuestionById(@RequestBody Question question, @PathVariable long idQuestion)
     {
         return null;
     }

    @GetMapping("/{idQuestion}")
    public Question getQuestionById(@PathVariable long idQuestion) {
        return questionService.getQuestionById(idQuestion)
                .orElseThrow(() -> new EntityNotFoundException("Pytanie o podanym identyfikatorze nie istnieje"));
    }

    @GetMapping("/getAll")
    public List<Question> getAll() {
        return questionService.getAllQuestions();
    }




}
