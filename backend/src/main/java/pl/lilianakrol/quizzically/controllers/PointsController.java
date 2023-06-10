package pl.lilianakrol.quizzically.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lilianakrol.quizzically.models.Quiz;
import pl.lilianakrol.quizzically.repositories.QuizRepository;

@RestController
@RequestMapping("/api/points")
public class PointsController {
    private final QuizRepository quizRepository;

    @Autowired
    public PointsController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/{username}")
    public long getTotalScoreByUsername(@PathVariable String username) {
        return quizRepository.findByUserUsername(username)
                .stream()
                .mapToLong(Quiz::getScore)
                .sum();
    }
}
