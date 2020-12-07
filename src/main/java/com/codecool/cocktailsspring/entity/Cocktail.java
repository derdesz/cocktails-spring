package com.codecool.cocktailsspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cocktail {

    @Id
    private String idDrink;

    @Column(unique = true)
    private String strDrink;

    private String strAlcoholic;

    @Lob
    private String strInstructions;

    private String strDrinkThumb;

    private String strIngredient1;

    private String strIngredient2;

    private String strIngredient3;

    private String strIngredient4;

    private String strIngredient5;

    private String strIngredient6;

    private String strIngredient7;

    private String strIngredient8;

    private String strIngredient9;

    private String strIngredient10;

    private String strIngredient11;

    private String strIngredient12;

    private String strIngredient13;

    private String strIngredient14;

    private String strIngredient15;

    private String strMeasure1;

    private String strMeasure2;

    private String strMeasure3;

    private String strMeasure4;

    private String strMeasure5;

    private String strMeasure6;

    private String strMeasure7;

    private String strMeasure8;

    private String strMeasure9;

    private String strMeasure10;

    private String strMeasure11;

    private String strMeasure12;

    private String strMeasure13;

    private String strMeasure14;

    private String strMeasure15;

    public List<String> createListOfIngredients() {
        List<String> listOfIngredients = new ArrayList<String>();
        for (int i = 1; i <= 15; i++) {
            try {
                Field measureField = Cocktail.class.getDeclaredField("strMeasure" + i);
                Field ingredientField = Cocktail.class.getDeclaredField("strIngredient" + i);
                String ingredient = (String) ingredientField.get(this);
                if (ingredient != null) {
                    String measure = (String) measureField.get(this);
                    StringBuilder sb = new StringBuilder();
                    if (measure != null) {
                        sb.append(measure).append(" ");
                    }
                    sb.append(ingredient);
                    listOfIngredients.add(sb.toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return listOfIngredients;

    }
}
