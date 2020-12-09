package com.codecool.cocktailsspring.security;

import com.codecool.cocktailsspring.entity.CocktailAppUser;
import com.codecool.cocktailsspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CocktailAppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//        String password = user.getPassword();
//        passwordEncoder.encode(password);
        return UserDetailsImpl.build(user);
    }
}
