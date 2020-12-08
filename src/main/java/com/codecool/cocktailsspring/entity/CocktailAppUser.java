package com.codecool.cocktailsspring.entity;

import com.codecool.cocktailsspring.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "cocktail_app_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public CocktailAppUser(@NonNull String email, @NonNull String password, @NonNull String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @ElementCollection
    private Set<Long> favouriteCocktailIds = new HashSet<>();

    public CocktailAppUser(@NonNull String email, @NonNull String password, @NonNull String name, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public void addFavouriteCocktail(Long cocktailId){
        favouriteCocktailIds.add(cocktailId);
    }
}
