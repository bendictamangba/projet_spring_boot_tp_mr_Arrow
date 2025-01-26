package com.tpnoel.reservation.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Récupérer les rôles de l'utilisateur authentifié
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Rediriger en fonction du rôle
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_CLIENT")) {
                response.sendRedirect("/");  // Redirige le client vers la racine "/"
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/dashboard");  // Redirige l'admin vers le tableau de bord "/dashboard"
                return;
            }
        }

        // Par défaut, redirection vers la page de connexion si aucun rôle valide
        response.sendRedirect("/login?error");
    }
}
