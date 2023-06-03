package pl.lilianakrol.quizzically.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lilianakrol.quizzically.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}