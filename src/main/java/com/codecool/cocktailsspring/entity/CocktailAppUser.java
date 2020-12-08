package com.codecool.cocktailsspring.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CocktailAppUser {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;

    @ElementCollection
    private Set<Long> favouriteCocktailIds = new HashSet<>();

    public void addFavouriteCocktail(Long cocktailId){
        favouriteCocktailIds.add(cocktailId);
    }
}
