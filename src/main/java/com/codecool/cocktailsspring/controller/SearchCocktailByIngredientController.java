package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SearchCocktailByIngredientController {
    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/search/{ingredient}")
    public ListOfDrinksItems searchCocktailByIngredient(@PathVariable("ingredient") String ingredient) {
        return cocktailAPIService.searchCocktailByIngredient(ingredient);
    }
}