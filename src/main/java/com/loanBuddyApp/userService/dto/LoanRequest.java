package com.loanBuddyApp.userService.dto;

import lombok.Data;

@Data
public class LoanRequest {
    private double loanAmount;
    private String loanDescription;
    private int creditScore;
    private boolean loanCollateral;
    private int ssnNumber;
    private double annualSalary;
    private int propertiesPossessed;
    private double netWorth;
    private String netWorthDocumentation;
}
