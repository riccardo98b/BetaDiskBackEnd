package com.betacom.dischi.DTO;

public class ProdottoOrdineDTO {

	private Integer id;
	private OrdineDTO ordine;
	private ProdottoDTO prodotto;
	private Integer quantita;
	private Double prezzoAcquisto;
	private ProdottoOrdineDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public OrdineDTO getOrdine() {
		return ordine;
	}
	public ProdottoDTO getProdotto() {
		return prodotto;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public Double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public static class Builder {
		private Integer id;
		private OrdineDTO ordine;
		private ProdottoDTO prodotto;
		private Integer quantita;
		private Double prezzoAcquisto;
		public Builder() {}
		public ProdottoOrdineDTO build() {
			ProdottoOrdineDTO dto = new ProdottoOrdineDTO();
			dto.id = this.id;
			dto.ordine = this.ordine;
			dto.prodotto = this.prodotto;
			dto.quantita = this.quantita;
			dto.prezzoAcquisto = this.prezzoAcquisto;
			return dto;
		}
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder ordine(OrdineDTO ordine) {
			this.ordine = ordine;
			return this;
		}
		public Builder prodotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
			return this;
		}
		public Builder quantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}
		public Builder prezzoAcquisto(Double prezzoAcquisto) {
			this.prezzoAcquisto = prezzoAcquisto;
			return this;
		}
		
	}
	
}
