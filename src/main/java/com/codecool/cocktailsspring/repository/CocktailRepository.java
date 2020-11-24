package com.codecool.cocktailsspring.repository;

import com.codecool.cocktailsspring.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
