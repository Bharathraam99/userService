package com.loanBuddyApp.userService.dto;

import com.loanBuddyApp.userService.model.User;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private User currentUser;
}
