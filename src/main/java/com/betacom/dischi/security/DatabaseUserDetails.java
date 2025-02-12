package com.betacom.dischi.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.betacom.dischi.models.Utente;

public class DatabaseUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L; // ID per la serializzazione

    private final Utente utente; // L'utente di cui stiamo gestendo i dettagli
    private final List<SimpleGrantedAuthority> authorities; // Le autorità (ruoli) dell'utente

    // Costruttore che prende l'utente come parametro e crea la lista delle autorità
    public DatabaseUserDetails(Utente utente) {
        this.utente = utente;

        // Crea una lista di autorità basata sul ruolo dell'utente
        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utente.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Restituisce la lista delle autorità dell'utente
        return authorities;
    }

    @Override
    public String getPassword() {
        // Restituisce la password dell'utente
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        // Restituisce lo username dell'utente
        return utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Restituisce true se l'account non è scaduto
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Restituisce true se l'account non è bloccato
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Restituisce true se le credenziali non sono scadute
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Restituisce true se l'account è abilitato
        return true;
    }
}