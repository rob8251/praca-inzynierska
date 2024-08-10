package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.AuthResponse;
import com.rutrob.recipesapi.rest.dto.LoginRequest;
import com.rutrob.recipesapi.rest.dto.SignUpRequest;
import com.rutrob.recipesapi.security.TokenProvider;
import com.rutrob.recipesapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.CharBuffer;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.createUser(signUpRequest);
        String token = authenticateAndGetToken(signUpRequest.username(), signUpRequest.password());
        Arrays.fill(signUpRequest.password(), '\0');
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.username(), loginRequest.password());
        Arrays.fill(loginRequest.password(), '\0');
        return new AuthResponse(token);
    }

    private String authenticateAndGetToken(String username, char[] password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, CharBuffer.wrap(password)));

        return tokenProvider.generate(authentication);
    }
}
