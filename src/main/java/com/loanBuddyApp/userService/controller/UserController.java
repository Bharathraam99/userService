package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<String> sayHello(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String tokenDetails = authorizationHeader.substring(7);
        String userName = jwtService.extractUserName(tokenDetails);
        return ResponseEntity.ok("Welcome to LoanBuddy User Mr/ Mrs. "+userName);
    }

}
