package net.groot.initializers;
 

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.groot.beans.Media;
import net.groot.repositories.MediaRepository;

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
//            media.setTitle("War and Peace");
//            media.setAuthor("Tolstoy");
//            media.setIsbn("67868687222278");
            
            media.setTitle(faker.book().title());
            media.setAuthor(faker.book().author());
            media.setIsbn(UUID.randomUUID().toString());

            mediaRepository.save(media);
        }

//        log.info("... finished media initialization");

    }
}
