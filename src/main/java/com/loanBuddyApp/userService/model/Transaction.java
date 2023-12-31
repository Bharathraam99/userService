package com.loanBuddyApp.userService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Transaction {

    private String transactionID;
    private String toUserName;
    private double amount;
    private Date timestamp;
    private String description;
}
