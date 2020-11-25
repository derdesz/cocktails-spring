package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.entity.Cocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CocktailsByAlcoholicController {
    @Autowired
    CocktailAPIService cocktailAPIService;

    @Autowired
    CocktailRepository cocktailRepository;

    @GetMapping("/filter/{alcoholic}")
    public List<Cocktail> getCocktailsBySpirit(@PathVariable("alcoholic") String alcoholic){
        return cocktailRepository.findCocktailsByStAndStrAlcoholic(alcoholic);
    }
}
