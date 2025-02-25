package com.betacom.dischi.request;

import java.util.List;

import com.betacom.dischi.DTO.ProdottoCarrelloDTO;

public class MailRequest {

	private String toEmail;
	private String nome;
	private String cognome;
	private Double totale;
	private String dataOrdine;
	private String password;
	private String username;
	private List<ProdottoCarrelloDTO> prodotti;
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
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
	public List<ProdottoCarrelloDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoCarrelloDTO> prodotti) {
		this.prodotti = prodotti;
	}
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	public String getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	
}
