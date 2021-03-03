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
        if(userId != null){
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

}
