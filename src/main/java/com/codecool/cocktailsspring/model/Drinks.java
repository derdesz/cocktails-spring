package com.codecool.cocktailsspring.model;

import com.codecool.cocktailsspring.entity.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drinks {
    private List<Cocktail> drinks;
}
