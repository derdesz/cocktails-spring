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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CocktailsByAlcoholicControllerTest {

    NewCocktail firstCocktail = new NewCocktail();
    NewCocktail anotherCocktail = new NewCocktail();
    NewCocktail nonAlcCocktail = new NewCocktail();


    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    CocktailsByAlcoholicController cocktailsByAlcoholicController;

    @Before
    public void init() {
        firstCocktail.setIdDrink("666666");
        firstCocktail.setStrDrink("GGGGGG");
        firstCocktail.setStrAlcoholic("alcoholic");
        firstCocktail.setStrInstructions("mix some happiness with fun");
        firstCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("happiness, fun")));
        cocktailRepository.save(firstCocktail);

        anotherCocktail.setIdDrink("888888");
        anotherCocktail.setStrDrink("XXXXXXX");
        anotherCocktail.setStrAlcoholic("alcoholic");
        anotherCocktail.setStrInstructions("mix some sunshine with fun");
        anotherCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("sunshine, fun")));
        cocktailRepository.save(anotherCocktail);

        nonAlcCocktail.setIdDrink("999999");
        nonAlcCocktail.setStrDrink("YYYYYY");
        nonAlcCocktail.setStrAlcoholic("non-alcoholic");
        nonAlcCocktail.setStrInstructions("mix some wit with humor");
        nonAlcCocktail.setAllIngredients(new ArrayList<String>(Collections.singleton("wit, humor")));
        cocktailRepository.save(nonAlcCocktail);
    }


    @Test
    public void testGetCocktailsBySpirit(){
        List<NewCocktail> foundCocktails = cocktailsByAlcoholicController.getCocktailsBySpirit("non-alcoholic");
        List<NewCocktail> expectedCocktailList = new ArrayList<>();
        expectedCocktailList.add(nonAlcCocktail);
        Assertions.assertEquals(expectedCocktailList, foundCocktails);
        Assertions.assertEquals(1, foundCocktails.size());
    }
}