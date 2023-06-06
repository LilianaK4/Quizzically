package pl.lilianakrol.quizzically.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.lilianakrol.quizzically.dto.QuizRequest;
import pl.lilianakrol.quizzically.dto.QuizResponse;
import pl.lilianakrol.quizzically.dto.QuizResultRequest;
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


     //Generate new quiz
     @PostMapping("/newQuiz")
     public ResponseEntity<QuizResponse> getQuiz(@RequestBody QuizRequest quizRequest) {
          QuizResponse quizResponse = quizService.newQuiz(quizRequest);
          return ResponseEntity.ok(quizResponse);
     }


     /*
     @PostMapping("/result")
     public ResponseEntity<Boolean> saveQuizResult(@RequestBody QuizResultRequest quizResultRequest) {
          int totalPoints = quizResultRequest.getTotalPoints();
          Long quizId = quizResultRequest.getQuizId();
          Long userId = quizResultRequest.getUserId();

          boolean savedSuccessfully = quizHistoryService.saveQuizHistory(totalPoints, quizId, userId);

          return ResponseEntity.ok(savedSuccessfully);
     }
*/


}
