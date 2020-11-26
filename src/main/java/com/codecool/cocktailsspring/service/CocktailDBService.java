package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.entity.Cocktail;
import com.codecool.cocktailsspring.model.Drinks;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CocktailDBService {
    @Autowired
    private CocktailRepository cocktailRepository;

    public void importCocktailData(){
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (Stream<Path> paths = Files.walk(Paths.get("./cocktails"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach( it -> {
                        try {
                            Drinks drinks = objectMapper.readValue(it.toFile(), Drinks.class);
                            if (drinks.getDrinks() == null) {
//                                System.out.println("Could not load drinks from : " + it);
                            } else {
                                for (Cocktail cocktail : drinks.getDrinks()) {
//                                    System.out.println("Cocktail : " + cocktail.getIdDrink() + " " + cocktail.getStrDrink());
                                    cocktailRepository.save(cocktail);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createStringsFromList (List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String listItem : list) {
            sb.append(listItem);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String capitalize (String string) {
        String firstLetter = String.valueOf(string.charAt(0)).toUpperCase();
        String restOfLetters = string.substring(1);
        System.out.println(firstLetter + restOfLetters);
        return firstLetter + restOfLetters;
    }

    public void createCocktail(NewCocktail cocktailData) {
        Cocktail cocktail = new Cocktail();
        cocktail.setIdDrink(UUID.randomUUID().toString());
        cocktail.setStrDrink(capitalize(cocktailData.getStrDrink()));
        cocktail.setStrAlcoholic(cocktailData.getStrAlcoholic());
        cocktail.setStrInstructions(cocktailData.getStrInstructions());
        cocktail.setAllIngredients(createStringsFromList(cocktailData.getAllIngredients()));
        System.out.println("create " + cocktail.getAllIngredients());
        cocktailRepository.save(cocktail);
    }
}
