package com.wms;


import com.wms.model.personne.Users;
import com.wms.repository.UsersRepository;
import com.wms.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WmsApplication implements CommandLineRunner {

	@Autowired
	private UsersServices usersServices;



	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);




	}

	@Override
	public void run(String... args) throws Exception {

		Users admin = new Users();
		admin.setName("hamza");
		admin.setPassword("123456");
		admin.setRole("Admin");
		admin.setEmail("hamza@gmail.com");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);

		usersServices.saveUser(admin);
	}
}
