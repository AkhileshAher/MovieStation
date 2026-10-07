package dev.akhileshaher.moviestation.controller;

import dev.akhileshaher.moviestation.dto.LoginRequestDTO;
import dev.akhileshaher.moviestation.dto.LoginResponseDTO;
import dev.akhileshaher.moviestation.dto.RegisterRequestDTO;
import dev.akhileshaher.moviestation.entity.User;
import dev.akhileshaher.moviestation.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNormalUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }
}
