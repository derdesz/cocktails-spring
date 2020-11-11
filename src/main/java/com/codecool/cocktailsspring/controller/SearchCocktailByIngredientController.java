package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCocktailByIngredientController {
    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/search-ingredient/{ingredient}")
    public ListOfDrinksItems searchCocktailByIngredient(@PathVariable("ingredient") String ingredient) {
        return cocktailAPIService.searchCocktailByIngredient(ingredient);
    }
}
