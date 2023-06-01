package pl.lilianakrol.quizzically.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lilianakrol.quizzically.dto.AuthenticationResponse;
import pl.lilianakrol.quizzically.dto.LoginRequest;
import pl.lilianakrol.quizzically.dto.RefreshTokenRequest;
import pl.lilianakrol.quizzically.dto.RegisterRequest;
import pl.lilianakrol.quizzically.models.User;
import pl.lilianakrol.quizzically.repositories.UserRepository;
import pl.lilianakrol.quizzically.repositories.VerificationTokenRepository;
import pl.lilianakrol.quizzically.service.AuthService;
import pl.lilianakrol.quizzically.service.RefreshTokenService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

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


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

}
