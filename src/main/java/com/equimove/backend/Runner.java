package com.equimove.backend;

import com.equimove.backend.entity.HorseEntity;
import com.equimove.backend.entity.UserEntity;
import com.equimove.backend.service.HorseService;
import com.equimove.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private HorseService horseService;

	@Override
	public void run(String... strings) throws Exception {

		UserEntity user1 = new UserEntity();
		user1.setFirstName("Jean");
		user1.setLastName("Dupond");
		user1.setEmail("j.dupond@gmail.com");
		user1.setPassword("1234");
		user1 = userService.create(user1);

		UserEntity user2 = new UserEntity();
		user2.setFirstName("Jeanne");
		user2.setLastName("Dupont");
		user2.setEmail("j.dupont@gmail.com");
		user2.setPassword("1234");
		user2 = userService.create(user2);

		HorseEntity horse1 = new HorseEntity();
		horse1.setName("Dudule");
		horse1.setOwner(user1);
		horse1 = horseService.create(horse1);

		HorseEntity horse2 = new HorseEntity();
		horse2.setName("Bichette");
		horse2.setOwner(user2);
		horse2 = horseService.create(horse2);
	}
}
