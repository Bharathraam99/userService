package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.dto.JwtAuthenticationResponse;
import com.loanBuddyApp.userService.dto.RefreshTokenRequest;
import com.loanBuddyApp.userService.dto.SigninRequest;
import com.loanBuddyApp.userService.dto.SignupRequest;
import com.loanBuddyApp.userService.model.User;
import com.loanBuddyApp.userService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User signup(SignupRequest signupRequest) throws Exception {


        if (userRepo.existsUserByUserName(signupRequest.getUserName())) {
            throw new Exception("Username already exists");
        }
        User user = User.builder()
                .userName(signupRequest.getUserName())
                .userAge(signupRequest.getUserAge())
                .userCountryName(signupRequest.getUserCountryName())
                .userPassword(passwordEncoder.encode(signupRequest.getUserPassword()))
                .userRole(signupRequest.getUserRole())
                .userFirstName(signupRequest.getUserFirstName())
                .userLastName(signupRequest.getUserLastName())
                .userPhoneNumber(signupRequest.getUserPhoneNumber())
                .userEmail(signupRequest.getUserEmail())
                .build();
        return userRepo.save(user);


    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUserName(), signinRequest.getUserPassword()));
        var user = userRepo.findByUserName(signinRequest.getUserName()).orElseThrow(() -> new IllegalArgumentException("Invalid Credentials !"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userName = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepo.findByUserName(userName).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }

        return null;
    }
}
