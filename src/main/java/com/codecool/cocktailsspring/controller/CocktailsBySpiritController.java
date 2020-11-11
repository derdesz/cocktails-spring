package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.model.listofcocktails.Spirit;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CocktailsBySpiritController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/by-spirit/{spiritname}")
    public ListOfDrinksItems getCocktailsBySpirit(@PathVariable("spiritname") String spiritname){
        return cocktailAPIService.getCocktailsBySpirit(spiritname);
    }
}
