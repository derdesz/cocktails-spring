package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.entity.CocktailAppUser;
import com.codecool.cocktailsspring.model.NewCocktail;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.repository.UserRepository;
import com.codecool.cocktailsspring.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class FavoriteController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CocktailRepository cocktailRepository;

    @PostMapping("/favorites/{cocktail_id}")
    public void saveFavoriteCocktail(@PathVariable("cocktail_id") String cocktailIdStr) {
        Long cocktailId = Long.parseLong(cocktailIdStr);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        if (userId != null) {
            Optional<CocktailAppUser> user = userRepository.findById(userId);
            if (user.isPresent()) {
                if (user.get().getFavouriteCocktailIds().contains(cocktailId)) {
                    user.get().getFavouriteCocktailIds().remove(cocktailId);
                } else {
                    user.get().getFavouriteCocktailIds().add(cocktailId);
                }
                userRepository.save(user.get());
            }
        }
    }

    @GetMapping("/favorites")
    public List<NewCocktail> getFavouriteCocktails() {
        System.out.println("Favorite");
        List<NewCocktail> foundCocktails = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());

        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        if (userId != null) {
            Optional<CocktailAppUser> user = userRepository.findById(userId);
            if (user.isPresent()) {
                if (!user.get().getFavouriteCocktailIds().isEmpty()) {
                    Set<Long> foundIds = userRepository.findById(userId).get().getFavouriteCocktailIds();
                    for (Long foundId : foundIds) {
                        foundCocktails.add(cocktailRepository.findCocktailByIdDrink(foundId.toString()));
                        System.out.println(cocktailRepository.findCocktailByIdDrink(foundId.toString()));
                    }
                    return foundCocktails;
                }
            }
        }
        return null;
    }

    @GetMapping("/is-favorite/{cocktail_id}")
    public String isFavorite(@PathVariable("cocktail_id") String cocktail_id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();

        if (userId != null) {
            Optional<CocktailAppUser> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Set<Long> foundIds = user.get().getFavouriteCocktailIds();
                if(foundIds.contains(cocktail_id)){
                    return "true";
                };
            }
        }
        return "false";
    }

}
