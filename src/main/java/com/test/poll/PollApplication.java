package com.test.poll;

import com.test.poll.dao.UserRepository;
import com.test.poll.domains.User;
import com.test.poll.domains.enums.Rol;
import com.test.poll.domains.enums.Status;
import com.test.poll.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PollApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordUtils passwordUtils;

	public static void main(String[] args) {
		SpringApplication.run(PollApplication.class, args);
	}

	@Override
	public void run(String... args) {
		this.initialData();
	}

	private void initialData() {
		this.userRepository.deleteAll();
		String password = "Admin";
		String mySecurePassword = this.passwordUtils.generateSecurePassword(password);

		User user = new User();
		user.setName("John Doe");
		user.setUsername("user");
		user.setRol(Rol.USER.name());
		user.setStatus(Status.ACTIVE.name());
		user.setPassword(mySecurePassword);

		User userAdmin = new User();
		userAdmin.setName("John Doe Admin");
		userAdmin.setUsername("admin");
		userAdmin.setRol(Rol.ADMIN.name());
		userAdmin.setStatus(Status.ACTIVE.name());
		userAdmin.setPassword(mySecurePassword);

		this.userRepository.save(user);
		this.userRepository.save(userAdmin);
	}
}
