package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocktailsByAlcoholicController {

    @Autowired
    CocktailRepository cocktailRepository;

    @GetMapping("/filter/{alcoholic}")
    public List<NewCocktail> getCocktailsBySpirit(@PathVariable("alcoholic") String alcoholic){
        return cocktailRepository.findCocktailsByStAndStrAlcoholic(alcoholic);
    }
}
