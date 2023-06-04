package pl.lilianakrol.quizzically.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lilianakrol.quizzically.models.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Long> {

    Optional<Question> findById(long id);

}
