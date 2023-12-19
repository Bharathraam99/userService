package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.dto.JwtAuthenticationResponse;
import com.loanBuddyApp.userService.dto.RefreshTokenRequest;
import com.loanBuddyApp.userService.dto.SigninRequest;
import com.loanBuddyApp.userService.dto.SignupRequest;
import com.loanBuddyApp.userService.model.User;
import com.loanBuddyApp.userService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {

        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {

        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
