package com.codecool.cocktailsspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCocktail {
    @Id
    private String idDrink;

    @Column(unique = true)
    private String strDrink;
    private String strAlcoholic;

    @Lob
    private String strInstructions;

    @Lob
    private List<String> allIngredients;
}
