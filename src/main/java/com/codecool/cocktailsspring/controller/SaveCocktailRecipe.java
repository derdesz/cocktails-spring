package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.service.CocktailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaveCocktailRecipe {

    @Autowired
    CocktailDBService cocktailDBService;

    @Autowired
    CocktailRepository cocktailRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save-new-cocktail")
    public void createCocktail(@RequestBody NewCocktail newCocktail) {
        cocktailDBService.createCocktail(newCocktail);
    }

    @GetMapping("/add-cocktail")
    public void addCocktail() {
    }
}
