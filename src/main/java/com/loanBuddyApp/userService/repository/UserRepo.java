package com.loanBuddyApp.userService.repository;

import com.loanBuddyApp.userService.model.Role;
import com.loanBuddyApp.userService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    User findByUserRole(Role userRole);
}
