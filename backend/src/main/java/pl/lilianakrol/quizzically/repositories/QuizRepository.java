package pl.lilianakrol.quizzically.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lilianakrol.quizzically.models.Quiz;
import pl.lilianakrol.quizzically.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByUser(User user);

    Optional<Quiz> findById(Long id);



}
