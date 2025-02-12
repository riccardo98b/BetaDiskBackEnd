package com.betacom.dischi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class Config {
    
    // Bean per caricare i dettagli dell'utente dal database
    @Bean
    DatabaseUserDetailsService userDetailsService() {
        // Crea e restituisce un'istanza del servizio per caricare i dettagli dell'utente dal database
        return new DatabaseUserDetailsService();
    }
    
    // Bean per la codifica delle password
    @Bean
    PasswordEncoder passwordEncoder() {
        // Restituisce un codificatore di password delegante (utilizza vari algoritmi)
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Bean per il provider di autenticazione con gestione della password codificata
    @Bean //provider componente che gestisce il processo di autenticazione
    DaoAuthenticationProvider authenticationProvider() {
        // Crea un'istanza di DaoAuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Imposta il servizio per caricare i dettagli dell'utente dal database
        authProvider.setUserDetailsService(userDetailsService());
        
        // Imposta il codificatore di password per verificare la password
        authProvider.setPasswordEncoder(passwordEncoder());

        // Restituisce il provider di autenticazione configurato
        return authProvider;
    }
}
