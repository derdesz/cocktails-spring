package com.codecool.cocktailsspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Lob
    private String allIngredients;

    public List<String> createListOfIngredients() {
        if(strAlcoholic.startsWith("Non")){
            strAlcoholic = "Non Alcoholic";
        }

        List<String> listOfIngredients = new ArrayList<String>();
        if (strIngredient1 != null) {
            listOfIngredients.add(strIngredient1);
        }
        if (strIngredient2 != null) {
            listOfIngredients.add(strIngredient2);
        }
        if (strIngredient3 != null) {
            listOfIngredients.add(strIngredient3);
        }
        if (strIngredient4 != null) {
            listOfIngredients.add(strIngredient4);
        }
        if (strIngredient5 != null) {
            listOfIngredients.add(strIngredient5);
        }
        if (strIngredient6 != null) {
            listOfIngredients.add(strIngredient6);
        }
        if (strIngredient7 != null) {
            listOfIngredients.add(strIngredient7);
        }
        if (strIngredient8 != null) {
            listOfIngredients.add(strIngredient8);
        }
        if (strIngredient9 != null) {
            listOfIngredients.add(strIngredient9);
        }
        if (strIngredient10 != null) {
            listOfIngredients.add(strIngredient10);
        }
        if (strIngredient11 != null) {
            listOfIngredients.add(strIngredient11);
        }
        if (strIngredient12 != null) {
            listOfIngredients.add(strIngredient12);
        }
        if (strIngredient13 != null) {
            listOfIngredients.add(strIngredient13);
        }
        if (strIngredient14 != null) {
            listOfIngredients.add(strIngredient14);
        }
        if (strIngredient15 != null) {
            listOfIngredients.add(strIngredient15);
        }
        return listOfIngredients;

    }
}
