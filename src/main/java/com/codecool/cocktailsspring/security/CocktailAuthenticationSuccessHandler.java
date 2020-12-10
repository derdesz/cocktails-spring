package com.codecool.cocktailsspring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CocktailAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtUtils jwtUtils;

    public CocktailAuthenticationSuccessHandler(){
        super();
        jwtUtils = new JwtUtils();
        jwtUtils.init();
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        String token =jwtUtils.generateJwtToken(user.getEmail(), roles);
        Cookie auth = new Cookie("auth", token);
        auth.setSecure(true);
        auth.setPath("/");
        response.addCookie(auth);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}


