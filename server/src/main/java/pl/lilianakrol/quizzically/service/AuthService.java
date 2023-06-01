package pl.lilianakrol.quizzically.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lilianakrol.quizzically.dto.AuthenticationResponse;
import pl.lilianakrol.quizzically.dto.LoginRequest;
import pl.lilianakrol.quizzically.dto.RefreshTokenRequest;
import pl.lilianakrol.quizzically.dto.RegisterRequest;
import pl.lilianakrol.quizzically.exceptions.QuizzicallyException;
import pl.lilianakrol.quizzically.models.NotificationEmail;
import pl.lilianakrol.quizzically.models.User;
import pl.lilianakrol.quizzically.models.VerificationToken;
import pl.lilianakrol.quizzically.repositories.UserRepository;
import pl.lilianakrol.quizzically.repositories.VerificationTokenRepository;
import pl.lilianakrol.quizzically.security.JwtProvider;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);
        String token = generateVerificationToken(user);

        // Publikowanie wiadomości do RabbitMQ
        NotificationEmail notificationEmail = new NotificationEmail("Quizzically - please activate your account", user.getEmail(),
                "Thank you for signing up to Quizzically, please click on the link below to activate your account: "
                        + "http://localhost:8080/api/auth/accountVerification/" + token);
        rabbitTemplate.convertAndSend("quizzically_verification_queue", notificationEmail);

    }




    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public boolean verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new QuizzicallyException("Invalid Token"));
        return fetchUserAndEnable(verificationToken.get());
    }

    private boolean fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new QuizzicallyException("User not found with username - " + username));
        if (!user.isEnabled()) {
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        else
            return false;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }


    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

}
