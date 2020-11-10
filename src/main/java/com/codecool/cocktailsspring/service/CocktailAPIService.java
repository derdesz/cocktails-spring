package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.byspirit.CocktailsBySpirit;
import com.codecool.cocktailsspring.model.byspirit.Spirit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CocktailAPIService {

    @Value("${cocktailsBySpirit.url}")
    private String cocktailsBySpiritURL ;

    public CocktailsBySpirit getCocktailsBySpirit(Spirit spirit) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<CocktailsBySpirit> cocktailsResponseEntity =
                template.exchange(cocktailsBySpiritURL + spirit, HttpMethod.GET, null, CocktailsBySpirit.class);
        return cocktailsResponseEntity.getBody();
    }

}
