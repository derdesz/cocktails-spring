package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.entity.Cocktail;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import com.codecool.cocktailsspring.service.CocktailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
public class SaveCocktailRecipe {

    @Autowired
    CocktailDBService cocktailDBService;

    @Autowired
    CocktailRepository cocktailRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save-new-cocktail")
    public void createCocktail(@RequestBody NewCocktail newCocktail) {
        System.out.println("save " + newCocktail);
        cocktailDBService.createCocktail(newCocktail);
        cocktailRepository.insertCocktail(newCocktail.getStrDrink(), newCocktail.getStrAlcoholic(), newCocktail.getStrInstructions(), cocktailDBService.createStringsFromList(newCocktail.getAllIngredients()));
    }
}
