package com.codecool.cocktailsspring;

import com.codecool.cocktailsspring.entity.CocktailAppUser;
import com.codecool.cocktailsspring.model.ERole;
import com.codecool.cocktailsspring.model.Role;
import com.codecool.cocktailsspring.repository.CocktailRepository;
import com.codecool.cocktailsspring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CocktailRepository cocktailRepository;

    public DataInitializer(UserRepository userRepository, CocktailRepository cocktailRepository) {
        this.userRepository = userRepository;
        this.cocktailRepository = cocktailRepository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) {

        Role admin = new Role(ERole.ROLE_ADMIN);

        CocktailAppUser dori = CocktailAppUser.builder()
                .email("georgia@cocktail.com")
                .password(passwordEncoder.encode("password"))
                .name("Georgia")
                .roles(Collections.singletonList(admin))
                .build();


//        CocktailAppUser katt = CocktailAppUser.builder()
//                .email("katt@cocktail.com")
//                .password(passwordEncoder.encode("password"))
//                .name("Katt")
//                .roles((Set<Role>) Role.builder().name(ERole.ROLE_ADMIN).build())
//                .build();
//
//        userRepository.save(CocktailAppUser.builder()
//                .email("gini@cocktail.com")
//                .password(passwordEncoder.encode("password"))
//                .name("Gini")
//                .roles((Set<Role>) Role.builder().name(ERole.ROLE_USER).build())
//                .build()
//        );
        userRepository.save(dori);

        log.debug("printing all users...");
//        userRepository.findAll().forEach(u -> log.debug(" ToDo App users :" + u.toString()));
    }
}
