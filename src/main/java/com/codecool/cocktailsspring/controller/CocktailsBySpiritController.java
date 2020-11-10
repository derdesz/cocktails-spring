package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.byspirit.CocktailsBySpirit;
import com.codecool.cocktailsspring.model.byspirit.Spirit;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailsBySpiritController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/by-spirit/{spiritname}")
    public CocktailsBySpirit getCocktailsBySpirit(@PathVariable("spiritname") Spirit spiritname){
        return cocktailAPIService.getCocktailsBySpirit(spiritname);
    }
}
