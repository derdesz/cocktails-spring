package com.codecool.cocktailsspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCocktail {
    private String strDrink;
    private String strAlcoholic;

    @Lob
    private String strInstructions;

    @Lob
    private List<String> allIngredients;
}
