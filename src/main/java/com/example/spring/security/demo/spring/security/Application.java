package com.example.spring.security.demo.spring.security;

import com.example.spring.security.demo.spring.security.entity.Role;
import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

//	@Bean
//	public static WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*")
//						.allowedOrigins("http://localhost:4200")
//						.allowedMethods(HttpMethod.GET.name(),
//								HttpMethod.POST.name(),
//								HttpMethod.DELETE.name(),
//								HttpMethod.PATCH.name(),
//								HttpMethod.PUT.name())
//						.allowedHeaders(HttpHeaders.CONTENT_TYPE,
//								HttpHeaders.AUTHORIZATION);
//			}
//		};
//	}

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
