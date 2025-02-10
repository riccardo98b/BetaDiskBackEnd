package com.betacom.dischi.DTO;

import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Prodotto;

public class ProdottoCarrelloDTO {

	private Integer id;
	private Carrello carrello;
	private Prodotto prodotto;
	private Integer quantita;
	private ProdottoCarrelloDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public Carrello getCarrello() {
		return carrello;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public Integer getQuantita() {
		return quantita;
	}
	
	public static class Builder {
		private Integer id;
		private Carrello carrello;
		private Prodotto prodotto;
		private Integer quantita;
		public Builder() {
		}
		public ProdottoCarrelloDTO build() {
			ProdottoCarrelloDTO dto = new ProdottoCarrelloDTO();
			dto.id = this.id;
			dto.carrello = this.carrello;
			dto.prodotto = this.prodotto;
			dto.quantita = this.quantita;
			return dto; 
		}
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder carrello(Carrello carrello) {
			this.carrello = carrello;
			return this;
		}
		public Builder prodotto(Prodotto prodotto) {
			this.prodotto = prodotto;
			return this;
		}
		public Builder quantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}
	}
	
}
