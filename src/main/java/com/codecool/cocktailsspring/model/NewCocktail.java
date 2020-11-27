package com.codecool.cocktailsspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ElementCollection
    private List<String> allIngredients = new ArrayList<>();
}
