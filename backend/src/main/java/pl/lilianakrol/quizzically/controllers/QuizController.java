package pl.lilianakrol.quizzically.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lilianakrol.quizzically.dto.QuizRequest;
import pl.lilianakrol.quizzically.dto.QuizResponse;
import pl.lilianakrol.quizzically.dto.ScoreUpdateRequest;
import pl.lilianakrol.quizzically.models.Quiz;
import pl.lilianakrol.quizzically.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
@AllArgsConstructor
public class QuizController {

     private QuizService quizService;

     @PostMapping()
     Quiz createQuiz(@RequestBody Quiz quiz)
     {
         return quiz;
     }

     //Generate new quiz
     @PostMapping("/newQuiz")
     public ResponseEntity<QuizResponse> getQuiz(@RequestBody QuizRequest quizRequest) {
          QuizResponse quizResponse = quizService.newQuiz(quizRequest);
          return ResponseEntity.ok(quizResponse);
     }

     @PatchMapping("/{quizId}/score")
     public ResponseEntity<String> updateQuizScore(@PathVariable Long quizId, @RequestBody ScoreUpdateRequest scoreUpdateRequest) {
          quizService.updateQuizScore(quizId, scoreUpdateRequest);
          return ResponseEntity.ok().build();
     }

}
