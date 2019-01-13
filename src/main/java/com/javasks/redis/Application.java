package com.javasks.redis;

import com.javasks.redis.model.User;
import com.javasks.redis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public Application(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) {

		LOG.info("Saving users. Current user count is {}.", userRepository.count());
		User shubham = new User("Shubham", 2000);
		User sandeep = new User("Sandeep", 550);

		userRepository.save(shubham);
		userRepository.save(sandeep);
		LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	}
}
