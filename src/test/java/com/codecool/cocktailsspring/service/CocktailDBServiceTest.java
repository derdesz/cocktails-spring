package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.Drinks;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.model.NewCocktailMapper;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
class CocktailDBServiceTest {

    @Autowired
    CocktailRepository cocktailRepository;

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
    void capitalizeStringStartingWithLowercaseLetter() {
        String word = "hello";
        String firstLetter = String.valueOf(word.charAt(0)).toUpperCase();
        String restOfLetters = word.substring(1);
        word = firstLetter + restOfLetters;
        Assertions.assertTrue(Character.isUpperCase(word.charAt(0)));


    }



    @Test
    void newCocktailIsSavedToRepository() {
        NewCocktail newCocktail = new NewCocktail();
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String randomName = new String(array, Charset.forName("UTF-8"));
        newCocktail.setIdDrink(UUID.randomUUID().toString());
        newCocktail.setStrDrink(randomName);
        newCocktail.setStrAlcoholic("alcoholic");
        newCocktail.setStrInstructions("instruction");
        newCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("ingr1, ingr2")));
        cocktailRepository.save(newCocktail);
        Assertions.assertEquals(randomName, newCocktail.getStrDrink());
    }

    @Test
    void filterCocktailsByIngredient() {
    }
}