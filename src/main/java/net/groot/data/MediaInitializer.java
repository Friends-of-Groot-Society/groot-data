package net.groot.data;
 

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import net.groot.data.entities.Media;
import net.groot.data.repositories.MediaRepository;

@Slf4j
@Component
public class MediaInitializer implements CommandLineRunner {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

//        log.info("Starting media initialization ...");

        for(int i = 0; i < 10; i++) {

            Media media = new Media();  
            
            media.setUniqueId(UUID.randomUUID().toString());
            media.setCharacter(faker.hobbit().character());
            media.setLocation(faker.hobbit().location()); 
            media.setThorinsCompany(faker.hobbit().thorinsCompany()); 
            media.setQuote(faker.hobbit().quote()); 

            mediaRepository.save(media);
        }

//        log.info("... finished media initialization");

    }
}
