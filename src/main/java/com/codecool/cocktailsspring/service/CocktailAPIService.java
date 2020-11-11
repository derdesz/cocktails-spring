package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.listofcocktails.Alcoholic;
import com.codecool.cocktailsspring.model.listofcocktails.ListOfDrinksItems;
import com.codecool.cocktailsspring.model.listofcocktails.Spirit;
import com.codecool.cocktailsspring.model.detailedcocktail.DetailedCocktail;
import com.codecool.cocktailsspring.model.spiritdescription.SpiritDescription;
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


    @Value("${spiritDescription.url}")
    private String spiritDescription;


    @Value("${filterCocktailsAlcoholic.url}")
    private String cocktailsAlcoholicURL ;

    @Value("${searchName.url}")
    private String cocktailByNameURL;

    @Value("${searchIngredient.url}")
    private String cocktailByIngredientURL;

    public ListOfDrinksItems getCocktailsBySpirit(String spirit) {

        RestTemplate template = new RestTemplate();
        ResponseEntity<ListOfDrinksItems> cocktailsResponseEntity =
                template.exchange(cocktailsBySpiritURL + spirit, HttpMethod.GET, null, ListOfDrinksItems.class);
        return cocktailsResponseEntity.getBody();
    }

    public ListOfDrinksItems getCocktailsByAlcoholic(String alcoholic) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ListOfDrinksItems> cocktailsResponseEntity =
                template.exchange(cocktailsAlcoholicURL + alcoholic, HttpMethod.GET, null, ListOfDrinksItems.class);
        return cocktailsResponseEntity.getBody();
    }

    public DetailedCocktail getRandomcocktail() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<DetailedCocktail> detailedCocktailResponseEntity =
                template.exchange(baseURL + "random.php", HttpMethod.GET, null, DetailedCocktail.class);
        return detailedCocktailResponseEntity.getBody();
    }


    public DetailedCocktail getCocktailById(String id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<DetailedCocktail> detailedCocktailResponseEntity =
                template.exchange(cocktailById + id, HttpMethod.GET, null, DetailedCocktail.class);
        return detailedCocktailResponseEntity.getBody();
    }



    public SpiritDescription getSpiritDescription (String spiritDescriptionName) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<SpiritDescription> spiritDescriptionResponseEntity = template.exchange(spiritDescription + spiritDescriptionName, HttpMethod.GET, null, SpiritDescription.class);
        return spiritDescriptionResponseEntity.getBody();
    }



    public DetailedCocktail searchCocktailByName(String name) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<DetailedCocktail> detailedCocktailResponseEntity =
                template.exchange(cocktailByNameURL + name, HttpMethod.GET, null, DetailedCocktail.class);
        return detailedCocktailResponseEntity.getBody();
    }

    public ListOfDrinksItems searchCocktailByIngredient(String ingredient) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ListOfDrinksItems> cocktailsResponseEntity =
                template.exchange(cocktailByIngredientURL + ingredient, HttpMethod.GET, null, ListOfDrinksItems.class);
        return cocktailsResponseEntity.getBody();
    }

}
