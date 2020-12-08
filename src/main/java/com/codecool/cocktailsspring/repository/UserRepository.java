package com.codecool.cocktailsspring.repository;

import com.codecool.cocktailsspring.entity.CocktailAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CocktailAppUser, Long> {
    Optional<CocktailAppUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
