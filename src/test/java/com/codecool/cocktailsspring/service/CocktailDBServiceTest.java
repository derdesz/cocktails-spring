package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.Drinks;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.model.NewCocktailMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class CocktailDBServiceTest {

    @Test
    void importedJsonContainsAll415CocktailRecipes() {
        RestTemplate restTemplate = new RestTemplate();
        String COCKTAILS_BY_LETTER = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=";
        List<List<NewCocktail>> newCocktails = new ArrayList<>();
        for (char character = 'a'; character <= 'z'; character++) {
            try {
                ResponseEntity<Drinks> responseEntity = restTemplate.exchange(COCKTAILS_BY_LETTER.concat(String.valueOf(character)), HttpMethod.GET, null, Drinks.class);
                newCocktails.add(NewCocktailMapper.listNewCocktails(responseEntity.getBody()));
            } catch (NullPointerException e) {
                continue;
            }
        }
        Integer number_of_cocktails = 415;
        AtomicInteger counter = new AtomicInteger();
        newCocktails.stream().forEach(newCocktails1 -> {
            newCocktails1.stream().forEach(newCocktail -> {
                counter.getAndIncrement();
            });
        });
        Assertions.assertEquals(number_of_cocktails, counter.intValue());
    }


    @Test
    void capitalize() {
        String word = "hello";
        String firstLetter = String.valueOf(word.charAt(0)).toUpperCase();
        String restOfLetters = word.substring(1);
        word = firstLetter + restOfLetters;
        Assertions.assertTrue(Character.isUpperCase(word.charAt(0)));


    }

    @Test
    void createCocktail() {
    }

    @Test
    void filterCocktailsByIngredient() {
    }
}