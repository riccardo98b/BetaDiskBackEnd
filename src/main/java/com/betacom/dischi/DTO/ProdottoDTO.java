package com.betacom.dischi.DTO;

import java.util.List;

public class ProdottoDTO {

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
	
	private List<OrdineDTO> ordini;
	
	private List<RecensioneDTO> recensioni;
	
		

	public ProdottoDTO() {
		super();
	}

	public ProdottoDTO(Integer idProdotto, String formato, String titolo, String artista, String genere,
			String descrizione, Integer annoPubblicazione, Double prezzo, Integer quantita, String immagineProdotto,
			List<OrdineDTO> ordini, List<RecensioneDTO> recensioni) {
		super();
		this.idProdotto = idProdotto;
		this.formato = formato;
		this.titolo = titolo;
		this.artista = artista;
		this.genere = genere;
		this.descrizione = descrizione;
		this.annoPubblicazione = annoPubblicazione;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.immagineProdotto = immagineProdotto;
		this.ordini = ordini;
		this.recensioni = recensioni;
	}

	
	
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

	public List<OrdineDTO> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineDTO> ordini) {
		this.ordini = ordini;
	}

	public List<RecensioneDTO> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<RecensioneDTO> recensioni) {
		this.recensioni = recensioni;
	}

	
	
	
}
