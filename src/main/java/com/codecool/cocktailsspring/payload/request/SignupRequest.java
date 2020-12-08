package com.codecool.cocktailsspring.payload.request;

import com.codecool.cocktailsspring.model.ERole;
import com.codecool.cocktailsspring.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> roles = new HashSet<>();

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String name;
}
