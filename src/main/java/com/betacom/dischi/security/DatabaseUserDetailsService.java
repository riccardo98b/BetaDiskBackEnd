package com.betacom.dischi.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.betacom.dischi.models.Utente;
import com.betacom.dischi.repository.IUtenteRepository;

public class DatabaseUserDetailsService implements UserDetailsService{
	
    // Inietta il repository per accedere ai dati degli utenti dal database
    private @Autowired IUtenteRepository utenteRepository;

	@Override
	// Questo metodo viene chiamato da Spring Security per caricare 
	//i dettagli dell'utente tramite username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente per il suo username nel database

		 Optional<Utente> optionalUtente = utenteRepository.findByUsername(username);
	        // Se l'utente non viene trovato, lancia un'eccezione
            if (!optionalUtente.isPresent()) {
	            throw new UsernameNotFoundException("Utente non trovato.");
	        }
            // Se l'utente viene trovato, ottieni i suoi dati
            Utente utente = optionalUtente.get();
            
            // Restituisce un oggetto User di Spring Security con username,
            //password e autorità (ruoli)
            
	        return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(), getAuthorities(utente));
	    
	}
	
	 /* CONTROLLO SE RUOLO E ADMIN O MENO */
    /* CONTROLLO SE RUOLO E ADMIN O MENO */
    private Collection<? extends GrantedAuthority> getAuthorities(Utente utente) {
        // Supponiamo che getRoles() restituisca una stringa (es. "ADMIN", "USER")
        String ruolo = utente.getRoles().toString(); // Restituisce il ruolo come stringa
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + ruolo));
    }
    

}
	

