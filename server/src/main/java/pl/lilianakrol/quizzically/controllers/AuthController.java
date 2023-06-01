package pl.lilianakrol.quizzically.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lilianakrol.quizzically.dto.RegisterRequest;
import pl.lilianakrol.quizzically.models.User;
import pl.lilianakrol.quizzically.repositories.UserRepository;
import pl.lilianakrol.quizzically.repositories.VerificationTokenRepository;
import pl.lilianakrol.quizzically.service.AuthService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        if(authService.verifyAccount(token))
            return new ResponseEntity<>("Account Activated Successfully", OK);
        else
            return new ResponseEntity<>("Account has been already activated", OK);
    }

}
