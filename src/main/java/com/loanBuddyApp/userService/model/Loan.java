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
    private String loanDescription;
    private int creditScore;
    private boolean loanCollateral;
    private int ssnNumber;
    private double annualSalary;
    private int propertiesPossessed;
    private double netWorth;
    private String netWorthDocumentation;
}
