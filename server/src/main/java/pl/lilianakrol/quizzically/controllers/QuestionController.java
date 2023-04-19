package pl.lilianakrol.quizzically.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.lilianakrol.quizzically.models.Question;


@RestController
@RequestMapping("/api/question")
public class QuestionController {

     @PostMapping() Question createQuiz(@RequestBody Question question)
     {
         return question;
     }

     @GetMapping("/{idQuestion}") Question getQuestionById(@PathVariable long idQuestion)
     {
         return null;
     }

     @PutMapping("/{idQuestion}") Question updateQuestionById(@RequestBody
     Question question, @PathVariable long idQuestion)
     {
         return null;
     }

     @DeleteMapping("/{idQuestion}") void deleteQuestionById(@PathVariable long idQuestion)
     {
         return;
     }

}
