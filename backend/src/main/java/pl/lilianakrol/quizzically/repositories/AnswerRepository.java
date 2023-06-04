package pl.lilianakrol.quizzically.repositories;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lilianakrol.quizzically.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
