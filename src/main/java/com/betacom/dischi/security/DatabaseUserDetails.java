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
	 
	 
	 /* GRANTED AUTHORITY DA IMPORTARE*/
	 private final List<SimpleGrantedAuthority> authorities; // Le autorità (ruoli) dell'utente

	   public DatabaseUserDetails(Utente utente) {
	        this.utente = utente;

	        // Crea una lista di autorità basata sul ruolo (una stringa)
	        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utente.getRoles()));
	    }

	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	     return authorities;
	 }

	 @Override
	 public String getPassword() {
	     return utente.getPassword();
	 }

	 @Override
	 public String getUsername() {
	     return utente.getUsername();
	 }

	 @Override
	 public boolean isAccountNonExpired() {
	     return true;
	 }

	 @Override
	 public boolean isAccountNonLocked() {
	     return true;
	 }

	 @Override
	 public boolean isCredentialsNonExpired() {
	     return true;
	 }

	 @Override
	 public boolean isEnabled() {
	     return true;
	 }
	}