package com.codecool.cocktailsspring.repository;

import com.codecool.cocktailsspring.model.NewCocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocktailRepository extends JpaRepository<NewCocktail, Long> {
    
    List<NewCocktail> findAllByStrDrinkContainingIgnoreCase(String name);

    @Query("SELECT c FROM NewCocktail c")
    List<NewCocktail> findCocktailByStrIngredient(@Param ("ingredient") String ingredient);

    @Query("SELECT c FROM NewCocktail c WHERE c.idDrink = :id")
    NewCocktail findCocktailByIdDrink(@Param ("id") String id);

    @Query("SELECT c FROM NewCocktail c WHERE c.strAlcoholic LIKE %:alcoholic%")
    List<NewCocktail> findCocktailsByStAndStrAlcoholic(@Param ("alcoholic") String alcoholic);

}