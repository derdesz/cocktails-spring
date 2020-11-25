package com.codecool.cocktailsspring.repository;

import com.codecool.cocktailsspring.entity.Cocktail;
import com.codecool.cocktailsspring.model.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    @Query("SELECT c FROM Cocktail c WHERE c.strDrink LIKE %:name%")
    List<Cocktail> findCocktailsByStrDrink(@Param ("name") String name);

    @Query("SELECT c FROM Cocktail c WHERE c.allIngredients LIKE %:ingredient%")
    List<Cocktail> findCocktailByStrIngredient(@Param ("ingredient") String ingredient);

    @Query("SELECT c FROM Cocktail c WHERE c.idDrink = :id")
    Cocktail findCocktailByIdDrink(@Param ("id") String id);


}