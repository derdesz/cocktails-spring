package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CocktailByIdController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @Autowired
    CocktailRepository cocktailRepository;

    @GetMapping("/{id}")
    public NewCocktail getCocktailById(@PathVariable("id") String id) {
        return cocktailRepository.findCocktailByIdDrink(id);
    }
}


