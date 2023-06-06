package pl.lilianakrol.quizzically.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lilianakrol.quizzically.dto.QuizRequest;
import pl.lilianakrol.quizzically.dto.QuizResponse;
import pl.lilianakrol.quizzically.models.Question;
import pl.lilianakrol.quizzically.models.Quiz;
import pl.lilianakrol.quizzically.models.User;
import pl.lilianakrol.quizzically.repositories.QuestionRepository;
import pl.lilianakrol.quizzically.repositories.QuizRepository;
import pl.lilianakrol.quizzically.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;
    private QuizRepository quizRepository;
    private UserRepository userRepository;


    public QuizResponse newQuiz(QuizRequest quizRequest) {
        Optional<User> userOptional = userRepository.findByUsername(quizRequest.getUsername());

        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Invalid username."));

        List<Question> questions = generateQuestions();

        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setStartTime(LocalDateTime.now());
        quiz.setScore(0);
        quiz.setQuestions(questions);



        quizRepository.save(quiz);

        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setQuizId(quiz.getId());
        quizResponse.setQuestions(questions);

        return quizResponse;
    }



    public List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();
        List<Question> allQuestions = questionRepository.findAll(); // Pobranie wszystkich pytań z bazy danych

        if (allQuestions.size() < 10) {
            return questions; // Zwróć pustą listę, jeśli w bazie jest za mało pytań
        }

        // Wygenerowanie 10 losowych pytań
        Random random = new Random();
        Set<Integer> selectedIndexes = new HashSet<>();

        while (selectedIndexes.size() < 10) {
            int randomIndex = random.nextInt(allQuestions.size());
            selectedIndexes.add(randomIndex);
        }

        for (int index : selectedIndexes) {
            questions.add(allQuestions.get(index));
        }

        return questions;
    }




}
