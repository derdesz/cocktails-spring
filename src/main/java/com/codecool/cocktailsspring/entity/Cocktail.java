package com.codecool.cocktailsspring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Clob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cocktail {

    @Id
    private String idDrink;

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

    @PrePersist
    public void prePersist() {
        StringBuilder sb = new StringBuilder();
        if (strIngredient1 != null) {
            sb.append(strIngredient1).append(" ");
        }
        if (strIngredient2 != null) {
            sb.append(strIngredient2).append(" ");
        }
        if (strIngredient3 != null) {
            sb.append(strIngredient3).append(" ");
        }
        if (strIngredient4 != null) {
            sb.append(strIngredient5).append(" ");
        }
        if (strIngredient6 != null) {
            sb.append(strIngredient6).append(" ");
        }
        if (strIngredient7 != null) {
            sb.append(strIngredient7).append(" ");
        }
        if (strIngredient8 != null) {
            sb.append(strIngredient8).append(" ");
        }
        if (strIngredient9 != null) {
            sb.append(strIngredient9).append(" ");
        }
        if (strIngredient10 != null) {
            sb.append(strIngredient10).append(" ");
        }
        if (strIngredient11 != null) {
            sb.append(strIngredient11).append(" ");
        }
        if (strIngredient12 != null) {
            sb.append(strIngredient12).append(" ");
        }
        if (strIngredient13 != null) {
            sb.append(strIngredient13).append(" ");
        }
        if (strIngredient14 != null) {
            sb.append(strIngredient14).append(" ");
        }
        if (strIngredient15 != null) {
            sb.append(strIngredient15).append(" ");
        }
        allIngredients = sb.toString().trim();

    }





}
