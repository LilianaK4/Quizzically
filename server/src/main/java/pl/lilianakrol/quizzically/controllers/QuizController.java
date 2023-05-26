package pl.lilianakrol.quizzically.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.lilianakrol.quizzically.models.Quiz;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

     @PostMapping()
     Quiz createQuiz(@RequestBody Quiz quiz)
     {
         return quiz;
     }

     @GetMapping("/{idQuiz}")
     Quiz getQuizById(@PathVariable long idQuiz)
     {
         return null;
     }

     @PutMapping("/{idQuiz}")
     Quiz updateQuizById(@RequestBody Quiz quiz, @PathVariable long idQuiz)
     {
         return null;
     }

     @DeleteMapping("/{idQuiz}")
     void deleteQuizById(@PathVariable long idQuiz)
     {
     return;
     }


}
