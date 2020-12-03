package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.entity.Cocktail;
import com.codecool.cocktailsspring.model.Drinks;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.model.NewCocktailMapper;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CocktailDBService {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Value("${cocktailsByLetter.url}")
    private String COCKTAILS_BY_LETTER;

//    public void importCocktailData(){
//        ObjectMapper objectMapper = new ObjectMapper()
//                .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true)
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try (Stream<Path> paths = Files.walk(Paths.get("./cocktails"))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach( it -> {
//                        try {
//                            Drinks drinks = objectMapper.readValue(it.toFile(), Drinks.class);
//                            if (drinks.getDrinks() != null) {
//                                for (Cocktail cocktail : drinks.getDrinks()) {
//                                    cocktailRepository.save(cocktail);
//                                }
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void importJSONData(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Drinks> responseEntity = restTemplate.exchange(COCKTAILS_BY_LETTER.concat("a"), HttpMethod.GET, null, Drinks.class);
        List<NewCocktail> newCocktails = NewCocktailMapper.listNewCocktails(responseEntity.getBody());
        for (NewCocktail cocktail : newCocktails) {
            cocktailRepository.save(cocktail);
        }
    }

    public String createStringsFromList (List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String listItem : list) {
            sb.append(listItem);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String capitalize (String string) {
        String firstLetter = String.valueOf(string.charAt(0)).toUpperCase();
        String restOfLetters = string.substring(1);
        System.out.println(firstLetter + restOfLetters);
        return firstLetter + restOfLetters;
    }

    public void createCocktail(NewCocktail cocktailData) {
        NewCocktail cocktail = new NewCocktail();
        cocktail.setIdDrink(UUID.randomUUID().toString());
        cocktail.setStrDrink(capitalize(cocktailData.getStrDrink()));
        cocktail.setStrAlcoholic(cocktailData.getStrAlcoholic());
        cocktail.setStrInstructions(cocktailData.getStrInstructions());
        cocktail.setAllIngredients(cocktailData.getAllIngredients());
//        System.out.println("create " + cocktail.getAllIngredients());
        cocktailRepository.save(cocktail);
    }

    public List<NewCocktail> filterCocktailsByIngredient(String ingredientName){
        return cocktailRepository.findAll()
                .stream()
                .filter(newCocktail -> {
                    for (String ingredient : newCocktail.getAllIngredients()) {
                        if (ingredient.contains(ingredientName)){
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
