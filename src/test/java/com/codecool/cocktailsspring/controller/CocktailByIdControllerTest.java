package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CocktailByIdControllerTest {

    @Autowired
    CocktailRepository cocktailRepository;

    @Test
    public void testGetCocktailById() {
        NewCocktail foundCocktail = cocktailRepository.findCocktailByIdDrink("11408");
        String expectedCocktail = "NewCocktail(idDrink=11408, strDrink=Gin Daisy, strAlcoholic=Alcoholic, strInstructions=In a shaker half-filled with ice cubes, combine the gin, lemon juice, sugar, and grenadine. Shake well. Pour into an old-fashioned glass and garnish with the cherry and the orange slice., allIngredients=[2 oz  Gin, 1 oz  Lemon juice, 1/2 tsp superfine  Sugar, 1/2 tsp  Grenadine, 1  Maraschino cherry, 1  Orange], strDrinkThumb=https://www.thecocktaildb.com/images/media/drink/z6e22f1582581155.jpg)";
        Assertions.assertEquals(expectedCocktail, foundCocktail.toString());
    }
}