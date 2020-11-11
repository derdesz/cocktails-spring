package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.model.spiritdescription.SpiritDescription;
import com.codecool.cocktailsspring.service.CocktailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SpiritDescriptionController {

    @Autowired
    CocktailAPIService cocktailAPIService;

    @GetMapping("/spirits/name/{spiritDescriptionName}")
    public SpiritDescription getSpiritDescription (@PathVariable("spiritDescriptionName") String spiritDescriptionName) {
        return cocktailAPIService.getSpiritDescription(spiritDescriptionName);
    }
}

