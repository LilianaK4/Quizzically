package pl.lilianakrol.quizzically.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.lilianakrol.quizzically.models.Question;
import pl.lilianakrol.quizzically.service.QuestionService;

import java.util.List;


@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionController {

    QuestionService questionService;

    @PostMapping()
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }


    @GetMapping("/{idQuestion}")
    public Question getQuestionById(@PathVariable long idQuestion) {
        return questionService.getQuestionById(idQuestion)
                .orElseThrow(() -> new EntityNotFoundException("No question found"));
    }

    @GetMapping("/getAll")
    public List<Question> getAll() {
        return questionService.getAllQuestions();
    }




}
