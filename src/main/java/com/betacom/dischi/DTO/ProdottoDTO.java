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
	
	
		

	private ProdottoDTO() {
		super();
	}

	public static class Builder {
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
		
		public Builder() {	
		}
		
		public ProdottoDTO build() {
		 ProdottoDTO prodotto = new ProdottoDTO();
		 prodotto.idProdotto = this.idProdotto;
		 prodotto.formato = this.formato;
		 prodotto.titolo = this.titolo;
		 prodotto.artista = this.artista;
		 prodotto.genere = this.genere;
		 prodotto.descrizione = this.descrizione;
		 prodotto.annoPubblicazione = this.annoPubblicazione;
		 prodotto.prezzo = this.prezzo;
		 prodotto.immagineProdotto = this.immagineProdotto;
		 prodotto.quantita = this.quantita;
		 prodotto.ordini = this.ordini;
		 prodotto.recensioni = this.recensioni;
		
		 return prodotto;
		 
		}

		public Builder idProdotto(Integer idProdotto) {
			this.idProdotto = idProdotto;
			return this;
		}

		public Builder formato(String formato) {
			this.formato = formato;
			return this;
		}

		public Builder titolo(String titolo) {
			this.titolo = titolo;
			return this;
		}

		public Builder artista(String artista) {
			this.artista = artista;
			return this;
		}

		public Builder genere(String genere) {
			this.genere = genere;
			return this;
		}

		public Builder descrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public Builder annoPubblicazione(Integer annoPubblicazione) {
			this.annoPubblicazione = annoPubblicazione;
			return this;
		}

		public Builder prezzo(Double prezzo) {
			this.prezzo = prezzo;
			return this;
		}

		public Builder quantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}

		public Builder immagineProdotto(String immagineProdotto) {
			this.immagineProdotto = immagineProdotto;
			return this;
		}

		public Builder ordini(List<OrdineDTO> ordini) {
			this.ordini = ordini;
			return this;
		}

		public Builder recensioni(List<RecensioneDTO> recensioni) {
			this.recensioni = recensioni;
			return this;
		}	
		
		
	}


}
