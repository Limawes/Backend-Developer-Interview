package br.com.mobiauto.security.controller;

import br.com.mobiauto.security.domain.request.LoginRequest;
import br.com.mobiauto.security.domain.response.JwtResponse;
import br.com.mobiauto.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        log.info("Authenticate user with data: {}", loginRequest.getUsername());
        JwtResponse jwtResponse = authService.login(loginRequest);
        return new ResponseEntity(jwtResponse, HttpStatus.OK);
    }
}
