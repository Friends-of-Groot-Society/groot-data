package net.groot.data;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GrootInitializer implements CommandLineRunner {

    @Autowired
    private GrootRepository grootRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

//        log.info("Starting groot initialization ...");

        for(int i = 0; i < 10; i++) {

            Groot groot = new Groot(); 
//            groot.setTitle("War and Peace");
//            groot.setAuthor("Tolstoy");
//            groot.setIsbn("67868687222278");
            
            groot.setIsbn(UUID.randomUUID().toString());
            groot.setTitle(faker.book().title());
            groot.setAuthor(faker.book().author());
            groot.setName(faker.book().publisher());
            groot.setType(faker.book().genre());

            grootRepository.save(groot);
        }

//        log.info("... finished groot initialization");

    }
}
