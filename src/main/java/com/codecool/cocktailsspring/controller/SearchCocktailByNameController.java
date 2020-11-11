package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCocktailByNameController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/search-name/{name}")
    public DetailedCocktail searchCocktailByName(@PathVariable("name") String name) {
        return cocktailAPIService.searchCocktailByName(name);
    }
}
