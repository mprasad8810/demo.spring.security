package com.example.spring.security.demo.spring.security;

import com.example.spring.security.demo.spring.security.entity.Role;
import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner{

	private final UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args){
		User admin = repository.findByRole(Role.ADMIN);
		if(null == admin){
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));

//					User.builder()
//					.firstname("admin")
//					.lastname("admin")
//					.email("admin@gmail.com")
//					.role(Role.ADMIN)
//					.password(new BCryptPasswordEncoder().encode("admin"))
//					.build();
			repository.save(user);
		}
	}

}
