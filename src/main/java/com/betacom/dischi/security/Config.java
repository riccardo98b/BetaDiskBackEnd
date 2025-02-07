package com.betacom.dischi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
	
    
	// Definisce il servizio per caricare i dettagli dell'utente dal database
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		// Crea un'istanza di DatabaseUserDetailsService
		return new DatabaseUserDetailsService();
	}
	
	// Definisce il codificatore di password
	@Bean
    PasswordEncoder passwordEncoder() {
		 // Restituisce un codificatore di password delegante
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// Configura il provider di autenticazione PASSWROD RICODIFICATA ALL'INVERSO
	// DECOFICA PASSWORD COFICIATA E FA CONFRONTO PER PERMETTIERCI DI ENTRARE
	@Bean
    DaoAuthenticationProvider authenticationProvider() {
		// Crea un'istanza di DaoAuthenticationProvider
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Imposta il servizio per caricare i dettagli dell'utente dal database
		authProvider.setUserDetailsService(userDetailsService());
		
        // Imposta il codificatore di password
		authProvider.setPasswordEncoder(passwordEncoder());

        // Può essere usato per stampare la password codificata (utile per debug)
		//System.out.println(passwordEncoder().encode("ciccio"));

		// Restituisce il provider di autenticazione configurato
		return authProvider;
	}

}
