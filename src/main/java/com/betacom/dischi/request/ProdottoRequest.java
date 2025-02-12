package com.betacom.dischi.request;

public class ProdottoRequest {
	
	private Integer idProdotto; 
	
	private String formato;
	
	private String titolo;
	
	private String artista;
	
	private String genere;
	
	private String descrizione;
	
	private Integer annoPubblicazione;
	
	private Double prezzo;
	
	private Integer quantita;
	
	private String immagineProdotto;
	
		

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(Integer annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getImmagineProdotto() {
		return immagineProdotto;
	}

	public void setImmagineProdotto(String immagineProdotto) {
		this.immagineProdotto = immagineProdotto;
	}
	
	
}
