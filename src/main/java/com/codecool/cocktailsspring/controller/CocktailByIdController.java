package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailByIdController {

    @Autowired
    CocktailRepository cocktailRepository;

    @GetMapping("/{id}")
    public NewCocktail getCocktailById(@PathVariable("id") String id) {
        return cocktailRepository.findCocktailByIdDrink(id);
    }
}


