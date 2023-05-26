package pl.lilianakrol.quizzically.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = LAZY)
    private User user;
    private Instant expiryDate;
}
