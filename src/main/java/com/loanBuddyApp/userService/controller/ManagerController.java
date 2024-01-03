package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.dto.ManagerLoanApprovalRequest;
import com.loanBuddyApp.userService.service.BankService;
import com.loanBuddyApp.userService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final JwtService jwtService;
    private final BankService bankService;

    @GetMapping
    public ResponseEntity<String> sayHello(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String tokenDetails = authorizationHeader.substring(7);
        String userName = jwtService.extractUserName(tokenDetails);
        return ResponseEntity.ok("Welcome to LoanBuddy Manager Mr/ Mrs. " + userName);
    }

    @PostMapping("/approveLoan")
    public ResponseEntity<?> approveLoan(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody ManagerLoanApprovalRequest managerLoanApprovalRequest) {
        try {
            String tokenDetails = authorizationHeader.substring(7);
            String userName = jwtService.extractUserName(tokenDetails);
            bankService.approveLoan(userName, managerLoanApprovalRequest);
            return ResponseEntity.ok("Loan approval successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}
