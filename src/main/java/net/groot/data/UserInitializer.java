package net.groot.data;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import net.groot.data.repositories.UserRepository;
import net.groot.data.entities.User;

@Slf4j
@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

//        log.info("Starting user initialization ...");

        for(int i = 0; i < 10; i++) {

            User user = new User(); 
//            user.setTitle("War and Peace");
//            user.setAuthor("Tolstoy");
//            user.setIsbn("67868687222278");
            
            user.setIsbn(UUID.randomUUID().toString());
            user.setTitle(faker.book().title());
            user.setAuthor(faker.book().author());
            user.setName(faker.book().publisher());
            user.setType(faker.book().genre());

            userRepository.save(user);
        }

//        log.info("... finished user initialization");

    }
}
