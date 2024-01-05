package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.dto.JwtAuthenticationResponse;
import com.loanBuddyApp.userService.dto.RefreshTokenRequest;
import com.loanBuddyApp.userService.dto.SigninRequest;
import com.loanBuddyApp.userService.dto.SignupRequest;
import com.loanBuddyApp.userService.model.User;
import com.loanBuddyApp.userService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        try {
            User user = authenticationService.signup(signupRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        try {
            JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signin(signinRequest);
            return ResponseEntity.ok(jwtAuthenticationResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
