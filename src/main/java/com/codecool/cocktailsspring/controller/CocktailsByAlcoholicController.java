package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.listofcocktails.Alcoholic;
import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailsByAlcoholicController {
    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/{alcoholic}")
    public ListOfDrinksItems getCocktailsBySpirit(@PathVariable("alcoholic") Alcoholic alcoholic){
        return cocktailAPIService.getCocktailsByAlcoholic(alcoholic);
    }
}
