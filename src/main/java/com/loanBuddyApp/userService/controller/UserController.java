package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.dto.LoanRequest;
import com.loanBuddyApp.userService.model.Loan;
import com.loanBuddyApp.userService.service.BankService;
import com.loanBuddyApp.userService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final BankService bankService;
    @GetMapping
    public ResponseEntity<String> sayHello(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String tokenDetails = authorizationHeader.substring(7);
        String userName = jwtService.extractUserName(tokenDetails);
        return ResponseEntity.ok("Welcome to LoanBuddy User Mr/ Mrs. "+userName);
    }

    @PostMapping("/requestLoan")
    public ResponseEntity<?> requestLoan(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody LoanRequest loanRequest){
        String tokenDetails = authorizationHeader.substring(7);
        String userName = jwtService.extractUserName(tokenDetails);
        try{
            Loan loan =bankService.loanRequest(userName, loanRequest);
            return ResponseEntity.ok(loan);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
