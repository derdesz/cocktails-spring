package com.codecool.cocktailsspring.controller;

import com.codecool.cocktailsspring.entity.CocktailAppUser;
import com.codecool.cocktailsspring.model.ERole;
import com.codecool.cocktailsspring.model.Role;
import com.codecool.cocktailsspring.payload.request.LoginRequest;
import com.codecool.cocktailsspring.payload.request.SignupRequest;
import com.codecool.cocktailsspring.payload.response.JwtResponse;
import com.codecool.cocktailsspring.payload.response.MessageResponse;
import com.codecool.cocktailsspring.repository.RoleRepository;
import com.codecool.cocktailsspring.repository.UserRepository;
import com.codecool.cocktailsspring.security.JwtUtils;
import com.codecool.cocktailsspring.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        CocktailAppUser user = new CocktailAppUser(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName()
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toString()) {
                    case "admin":
                        roles.add(new Role(ERole.ROLE_ADMIN));

                        break;
                    default:
                        roles.add(new Role(ERole.ROLE_USER));
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<CocktailAppUser> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @GetMapping("/log-out")
    public ResponseEntity<String> logOutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        response.addCookie(new Cookie("auth", ""));
        response.addCookie(new Cookie("email", ""));

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/login_error")
    public ResponseEntity loginError(){
        return ResponseEntity.status(403).build();
    }
}
