package com.loanBuddyApp.userService.controller;

import com.loanBuddyApp.userService.dto.TransactionRequest;
import com.loanBuddyApp.userService.model.Transaction;
import com.loanBuddyApp.userService.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody TransactionRequest transactionRequest) throws Exception {
        try{
            Transaction transaction =bankService.transferMoney(transactionRequest,authorizationHeader);
            return ResponseEntity.ok(transaction);

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);

        }
    }
}
