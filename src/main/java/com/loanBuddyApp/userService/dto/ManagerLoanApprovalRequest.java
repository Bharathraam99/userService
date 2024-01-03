package com.loanBuddyApp.userService.dto;

import lombok.Data;

@Data
public class ManagerLoanApprovalRequest {

    private String loanSecurer;
    private String loanID;
    private double loanInterestRate;
    private int timeInMonths;
    private boolean loanSanctioned;
    private double approvedLoanAmount;
}
