package com.codecool.cocktailsspring.model;

import com.codecool.cocktailsspring.entity.Cocktail;

import java.util.ArrayList;
import java.util.List;


public class NewCocktailMapper {
    public static NewCocktail cocktailMapper(Cocktail cocktail){
        return new NewCocktail(cocktail.getIdDrink(), cocktail.getStrDrink(), cocktail.getStrAlcoholic(), cocktail.getStrInstructions(), cocktail.createListOfIngredients(), cocktail.getStrDrinkThumb());
    }

    public static List<NewCocktail> listNewCocktails(Drinks drinks){
        List<NewCocktail> newCocktailList = new ArrayList<>();
        for (Cocktail cocktail: drinks.getDrinks()){
            newCocktailList.add(cocktailMapper(cocktail));
        }
        return newCocktailList;
    }
}
