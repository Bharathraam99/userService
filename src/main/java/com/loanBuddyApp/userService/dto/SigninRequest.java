package com.loanBuddyApp.userService.dto;

import com.loanBuddyApp.userService.model.Role;
import lombok.Data;

@Data
public class SigninRequest {

    private String userName;
    private String userPassword;

}
