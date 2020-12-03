package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.service.CocktailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SearchCocktailByIngredientController {

    @Autowired
    CocktailDBService cocktailDBService;

    @GetMapping("/search/ingredient/{ingredient}")
    public List<NewCocktail> findCocktailByStrIngredient(@PathVariable("ingredient") String ingredient) {
        return cocktailDBService.filterCocktailsByIngredient(ingredient);
    }

}
