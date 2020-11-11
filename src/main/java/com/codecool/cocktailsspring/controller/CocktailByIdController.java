package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
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

    @GetMapping("/{id}")
    public DetailedCocktail getCocktailById(@PathVariable("id") int id) {
        return cocktailAPIService.getCocktailById(id);
    }
}


