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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
    void newUniqueCocktailIsSavedToRepository() {
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
    void newExistingCocktailIsSavedToRepository() {
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

        NewCocktail newCocktail1 = new NewCocktail();
        newCocktail1.setIdDrink(UUID.randomUUID().toString());
        newCocktail1.setStrDrink(randomName);
        newCocktail1.setStrAlcoholic("alcoholic");
        newCocktail1.setStrInstructions("instruction");
        newCocktail1.setAllIngredients(new ArrayList<String>(Collections.singleton("ingr1, ingr2")));
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> cocktailRepository.save(newCocktail1));
    }

    @Test
    void filterCocktailsContainingIngredient() {
        NewCocktail newCocktail1 = new NewCocktail();
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String randomName = new String(array, Charset.forName("UTF-8"));
        newCocktail1.setIdDrink(UUID.randomUUID().toString());
        newCocktail1.setStrDrink(randomName);
        newCocktail1.setStrAlcoholic("alcoholic");
        newCocktail1.setStrInstructions("instruction");
        newCocktail1.setAllIngredients(new ArrayList<String>(Collections.singleton("ingr1, ingr2")));
        cocktailRepository.save(newCocktail1);

        NewCocktail newCocktail2 = new NewCocktail();
        byte[] array2 = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array2);
        String randomName2 = new String(array2, Charset.forName("UTF-8"));
        newCocktail2.setIdDrink(UUID.randomUUID().toString());
        newCocktail2.setStrDrink(randomName2);
        newCocktail2.setStrAlcoholic("alcoholic");
        newCocktail2.setStrInstructions("instruction");
        newCocktail2.setAllIngredients(new ArrayList<String>(Collections.singleton("ingr1, ingr2")));
        cocktailRepository.save(newCocktail2);

        List<NewCocktail> cocktailsHavingSpecificIngredient = cocktailRepository.findAll()
                .stream()
                .filter(newCocktail -> {
                    for (String ingredient : newCocktail.getAllIngredients()) {
                        if (ingredient.contains("in")) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        Assertions.assertTrue(cocktailsHavingSpecificIngredient.contains(newCocktail1)&&cocktailsHavingSpecificIngredient.contains(newCocktail2));
    }
}