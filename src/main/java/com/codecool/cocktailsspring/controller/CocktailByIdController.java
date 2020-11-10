package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.byspirit.CocktailsBySpirit;
import com.codecool.cocktailsspring.model.byspirit.Spirit;
import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailByIdController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/{id}")
    public DetailedCocktail getCocktailById(@PathVariable("id") int id) {
        return cocktailAPIService.getCocktailById(id);
    }
}

