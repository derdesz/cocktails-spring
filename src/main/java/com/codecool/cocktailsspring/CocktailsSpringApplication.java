package com.codecool.cocktailsspring;

import com.codecool.cocktailsspring.service.CocktailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CocktailsSpringApplication {
    @Autowired
    CocktailDBService cocktailDBService;

    public static void main(String[] args) {
        SpringApplication.run(CocktailsSpringApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init(){
        return args -> {
            cocktailDBService.importJSONData();
//            cocktailDBService.importCocktailData();
        };
    }
}
