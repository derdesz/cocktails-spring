package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.byspirit.CocktailsBySpirit;
import com.codecool.cocktailsspring.model.byspirit.Spirit;
import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CocktailAPIService {

    @Value("${cocktailsBySpirit.url}")
    private String cocktailsBySpiritURL;

    @Value("${baseURL.url}")
    private String baseURL;

    @Value("${cocktailById.url}")
    private String cocktailById;

    public CocktailsBySpirit getCocktailsBySpirit(Spirit spirit) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<CocktailsBySpirit> cocktailsResponseEntity =
                template.exchange(cocktailsBySpiritURL + spirit, HttpMethod.GET, null, CocktailsBySpirit.class);
        return cocktailsResponseEntity.getBody();
    }


    public DetailedCocktail getRandomcocktail() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<DetailedCocktail> detailedCocktailResponseEntity =
                template.exchange(baseURL + "random.php", HttpMethod.GET, null, DetailedCocktail.class);
        return detailedCocktailResponseEntity.getBody();
    }


    public DetailedCocktail getCocktailById(int id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<DetailedCocktail> detailedCocktailResponseEntity =
                template.exchange(cocktailById + id, HttpMethod.GET, null, DetailedCocktail.class);
        return detailedCocktailResponseEntity.getBody();
    }

}
