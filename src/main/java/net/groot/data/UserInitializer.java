package net.groot.data;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import net.groot.data.repositories.UserRepository;
import net.groot.data.repositories.MediaRepository;
import net.groot.data.entities.User;
import net.groot.data.entities.Media;

@Slf4j
@Component
public class UserInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepository mediaRepository;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker();

//        log.info("Starting user initialization ...");

		for (int i = 0; i < 10; i++) {

			User user = new User();

//
			user.setTokenId(UUID.randomUUID().toString());
//			user.setTokenId(faker.idNumber().ssnValid());
			user.setEmail(faker.internet().emailAddress());
			user.setPassword(faker.internet().password());
			user.setfName(faker.howIMetYourMother().character());
			user.setlName(faker.superhero().name());
			user.setMemberSince(faker.date().toString());
			user.setGroupType(faker.superhero().power());
			
			Media media = new Media();
			
			media.setUniqueId(UUID.randomUUID().toString());
			media.setCharacter(faker.hobbit().character());
			media.setLocation(faker.hobbit().location());
			media.setThorinsCompany(faker.hobbit().thorinsCompany());
			media.setQuote(faker.hobbit().quote());
			mediaRepository.save(media);
			
			user.setMedia(media);

			userRepository.save(user);
		}

//        log.info("... finished user initialization");

	}
}
