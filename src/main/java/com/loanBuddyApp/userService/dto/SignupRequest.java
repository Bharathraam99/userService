package com.loanBuddyApp.userService.dto;

import com.loanBuddyApp.userService.model.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userEmail;
    private int userAge;
    private String userCountryName;
    private String userPhoneNumber;
    private Role userRole;

}
