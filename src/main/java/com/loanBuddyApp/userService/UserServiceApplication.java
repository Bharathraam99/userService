package com.loanBuddyApp.userService;

import com.loanBuddyApp.userService.model.Role;
import com.loanBuddyApp.userService.model.User;
import com.loanBuddyApp.userService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin =userRepo.findByUserRole(Role.ADMIN);
		if(null == admin){
			User user = User.builder()
					.userName("bharath18")
					.userAge(24)
					.userPassword(new BCryptPasswordEncoder().encode("qwerty"))
					.userEmail("bharath@gmail.com")
					.userFirstName("Bharath")
					.userLastName("Raam")
					.userPhoneNumber("224-504-3411")
					.userCountryName("India")
					.userRole(Role.ADMIN)
					.build();
			userRepo.save(user);
		}
	}
}
