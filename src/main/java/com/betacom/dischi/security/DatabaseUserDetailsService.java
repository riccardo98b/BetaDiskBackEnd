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

public class DatabaseUserDetailsService implements UserDetailsService {

    // Inietta il repository per accedere ai dati degli utenti nel database
    private @Autowired IUtenteRepository utenteRepository;

    @Override
    // Questo metodo viene chiamato da Spring Security per caricare 
    // i dettagli dell'utente tramite il suo username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente nel database tramite il suo username
        Optional<Utente> optionalUtente = utenteRepository.findByUsername(username);

        // Se l'utente non è trovato, lancia un'eccezione
        if (!optionalUtente.isPresent()) {
            throw new UsernameNotFoundException("Utente non trovato.");
        }

        // Ottieni l'utente dalla risposta del database
        Utente utente = optionalUtente.get();

        // Restituisce un oggetto User di Spring Security con username, password e autorità (ruoli)
        return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(), getAuthorities(utente));
    }

    // Metodo per ottenere le autorità (ruoli) dell'utente
    private Collection<? extends GrantedAuthority> getAuthorities(Utente utente) {
        // Ottieni il ruolo dell'utente come stringa (es. "ADMIN", "USER")
        String ruolo = utente.getRoles().toString(); // Restituisce il ruolo come stringa

        // Crea una lista di ruoli per Spring Security (es. ROLE_ADMIN)
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + ruolo));
    }
}
