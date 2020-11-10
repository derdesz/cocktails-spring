package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.listofcocktails.Alcoholic;
import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.model.listofcocktails.Spirit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CocktailAPIService {

    @Value("${cocktailsBySpirit.url}")
    private String cocktailsBySpiritURL ;


    @Value("${filterCocktailsAlcoholic.url}")
    private String cocktailsAlcoholicURL ;

    public ListOfDrinksItems getCocktailsBySpirit(Spirit spirit) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ListOfDrinksItems> cocktailsResponseEntity =
                template.exchange(cocktailsBySpiritURL + spirit, HttpMethod.GET, null, ListOfDrinksItems.class);
        return cocktailsResponseEntity.getBody();
    }

    public ListOfDrinksItems getCocktailsByAlcoholic(Alcoholic alcoholic) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ListOfDrinksItems> cocktailsResponseEntity =
                template.exchange(cocktailsAlcoholicURL + alcoholic, HttpMethod.GET, null, ListOfDrinksItems.class);
        return cocktailsResponseEntity.getBody();
    }
}
