package com.betacom.dischi.request;

import java.util.List;

import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Ordine;
import com.betacom.dischi.models.Recensione;
import com.betacom.dischi.models.Utente;
import com.betacom.dischi.models.Wishlist;



public class ClienteRequest {
	

	private Integer idCliente;
	
	private String nome;
	
	private String cognome;
	
	private String telefono;
	
	private String immagineCliente;
	
	private List<Ordine> ordini;
	
	private Carrello carrello;
	
	private Utente utente;
	
    private Wishlist wishlist;
	
    private List<Recensione> recensioni;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getImmagineCliente() {
		return immagineCliente;
	}

	public void setImmagineCliente(String immagineCliente) {
		this.immagineCliente = immagineCliente;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
    
    

}
