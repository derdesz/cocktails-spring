package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CocktailByIdControllerTest {
    NewCocktail expectedCocktail = new NewCocktail();
    NewCocktail anotherCocktail = new NewCocktail();

    @Before
    public void init() {
        expectedCocktail.setIdDrink("666666");
        expectedCocktail.setStrDrink("GGGGGG");
        expectedCocktail.setStrAlcoholic("alcoholic");
        expectedCocktail.setStrInstructions("mix some happiness with fun");
        expectedCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("happiness, fun")));
        cocktailRepository.save(expectedCocktail);

        anotherCocktail.setIdDrink("888888");
        anotherCocktail.setStrDrink("XXXXXXX");
        anotherCocktail.setStrAlcoholic("alcoholic");
        anotherCocktail.setStrInstructions("mix some sunshine with fun");
        anotherCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("sunshine, fun")));
        cocktailRepository.save(anotherCocktail);
    }

    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    CocktailByIdController cocktailByIdController;

    @Test
    public void testGetCocktailById() {
        NewCocktail foundCocktail = cocktailByIdController.getCocktailById("666666");
        Assertions.assertEquals(expectedCocktail, foundCocktail);
    }

    @Test
    public void testGetCocktailByNonExistentId() {
        NewCocktail foundCocktail = cocktailByIdController.getCocktailById("1111");
        Assertions.assertNull(foundCocktail);
    }
}