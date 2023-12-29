package com.loanBuddyApp.userService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan {

    private String loanID;
    private double loanAmount;
    private double loanInterestRate;
    private int timeInMonths;
    private boolean loanSanctioned;
}
