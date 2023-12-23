package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.dto.JwtAuthenticationResponse;
import com.loanBuddyApp.userService.dto.RefreshTokenRequest;
import com.loanBuddyApp.userService.dto.SigninRequest;
import com.loanBuddyApp.userService.dto.SignupRequest;
import com.loanBuddyApp.userService.model.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest) throws Exception;
    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
