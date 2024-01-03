package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.dto.LoanRequest;
import com.loanBuddyApp.userService.dto.ManagerLoanApprovalRequest;
import com.loanBuddyApp.userService.dto.TransactionRequest;
import com.loanBuddyApp.userService.model.Loan;
import com.loanBuddyApp.userService.model.Transaction;

public interface BankService {

    Transaction transferMoney(TransactionRequest transactionRequest, String authorizationHeader) throws Exception;

    Loan loanRequest(String userName, LoanRequest loanRequest);

    void approveLoan(String userName, ManagerLoanApprovalRequest managerLoanApprovalRequest) throws Exception;
}
