package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.service.CocktailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocktailsBySpiritController {

    @Autowired
    CocktailDBService cocktailDBService;

    @Autowired
    CocktailRepository cocktailRepository;

    @GetMapping("/by-spirit/{spiritname}")
    public List<NewCocktail> getCocktailsBySpirit(@PathVariable("spiritname") String spiritname) {
        return cocktailDBService.filterCocktailsByIngredient(spiritname);
    }
}
