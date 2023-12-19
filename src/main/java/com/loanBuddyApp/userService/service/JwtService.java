package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;


public interface JwtService {

    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
