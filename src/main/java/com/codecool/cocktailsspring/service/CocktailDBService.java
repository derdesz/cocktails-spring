package com.codecool.cocktailsspring.service;

import com.codecool.cocktailsspring.model.Drinks;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.model.NewCocktailMapper;
import com.codecool.cocktailsspring.repository.CocktailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailDBService {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Value("${cocktailsByLetter.url}")
    private String COCKTAILS_BY_LETTER;


    public void importJSONData() {
        char currentChar = 'a';
        List<List<NewCocktail>> newCocktails = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for (char character = currentChar; character <= 'z'; character++) {
            try {
                ResponseEntity<Drinks> responseEntity = restTemplate.exchange(COCKTAILS_BY_LETTER.concat(String.valueOf(character)), HttpMethod.GET, null, Drinks.class);
                newCocktails.add(NewCocktailMapper.listNewCocktails(responseEntity.getBody()));
            } catch (NullPointerException e) {
                continue;
            }
        }

        List<NewCocktail> allNewCocktailsList = newCocktails.stream().flatMap(List::stream).collect(Collectors.toList());
        for (NewCocktail cocktail : allNewCocktailsList) {
            cocktailRepository.save(cocktail);
        }
    }


    public String createStringsFromList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String listItem : list) {
            sb.append(listItem);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String capitalize(String string) {
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

    public List<NewCocktail> filterCocktailsByIngredient(String ingredientName) {
        return cocktailRepository.findAll()
                .stream()
                .filter(newCocktail -> {
                    for (String ingredient : newCocktail.getAllIngredients()) {
                        if (ingredient.contains(ingredientName)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
