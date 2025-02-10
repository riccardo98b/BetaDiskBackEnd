package com.betacom.dischi.DTO;

import java.util.List;

public class CarrelloDTO {

	private Integer idCarrello;
	private Double totale;
	private List<ProdottoCarrelloDTO> prodotti;
	private ClienteDTO cliente;
	
	private CarrelloDTO() {
		super();
	}	
	public Integer getIdCarrello() {
		return idCarrello;
	}
	public Double getTotale() {
		return totale;
	}
	public List<ProdottoCarrelloDTO> getProdotti() {
		return prodotti;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
		
	public static class Builder {
		private Integer idCarrello;
		private Double totale;
		private List<ProdottoCarrelloDTO> prodotti;
		private ClienteDTO cliente;
		public Builder() {
		}
		public CarrelloDTO build() {
			CarrelloDTO dto = new CarrelloDTO();
			dto.idCarrello = this.idCarrello;
			dto.totale = this.totale;
			dto.prodotti = this.prodotti;
			dto.cliente = this.cliente;
			return dto;
		}
		public Builder idCarrello(Integer idCarrello) {
			this.idCarrello = idCarrello;
			return this;
		}
		public Builder totale(Double totale) {
			this.totale = totale;
			return this;
		}
		public Builder prodotti(List<ProdottoCarrelloDTO> prodotti) {
			this.prodotti = prodotti;
			return this;
		}
		public Builder cliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}
	}	
	
}
