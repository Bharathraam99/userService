package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<String> sayHello(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String tokenDetails = authorizationHeader.substring(7);
        String userName = jwtService.extractUserName(tokenDetails);
        return ResponseEntity.ok("Welcome to LoanBuddy Admin Mr/ Mrs. " + userName);
    }

}
