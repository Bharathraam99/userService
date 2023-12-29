package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.dto.TransactionRequest;
import com.loanBuddyApp.userService.model.Transaction;

public interface BankService {

    Transaction transferMoney(TransactionRequest transactionRequest, String authorizationHeader) throws Exception;
}
