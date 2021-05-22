package me.wirries.demo.springodata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class SpringODataApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringODataApplication.class);

    /**
     * Start the Spring Boot application and pass the command line arguments
     * to the application.
     *
     * @param args Array of command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringODataApplication.class, args);
        LOGGER.info("Spring Boot application stated");
    }

}
