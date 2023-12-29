package com.loanBuddyApp.userService.dto;

import lombok.Data;


@Data
public class TransactionRequest {
    private String toUserName;
    private double amount;
    private String description;
}
