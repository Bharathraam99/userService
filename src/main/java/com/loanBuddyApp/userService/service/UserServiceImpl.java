package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

@Override
public UserDetailsService userDetailsService(){
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found !"));
        }
    };
}

}
