package com.betacom.dischi.DTO;

import java.util.List;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.ProdottoDTO;

public class CarrelloDTO {

	private Integer idCarrello;
	private Double totale;
	private List<ProdottoDTO> prodotti;
	private ClienteDTO cliente;
	private Integer quantita;
	private CarrelloDTO() {
	}
	public  static class Builder{
		private Integer idCarrello;
		private Double totale;
		private List<ProdottoDTO> prodotti;
		private ClienteDTO cliente;
		private Integer quantita;
		public Builder setIdCarrello(Integer idCarrello) {
			this.idCarrello = idCarrello;
			return this;
		}
		public Builder setTotale(Double totale) {
			this.totale = totale;
			return this;
		}
		public Builder setProdotti(List<ProdottoDTO> prodotti) {
			this.prodotti = prodotti;
			return this;
		}
		public Builder setCliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}
		public Builder setQuantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}
		public CarrelloDTO build() {
			CarrelloDTO carrelloDTO = new CarrelloDTO();
			carrelloDTO.idCarrello = this.idCarrello;
			carrelloDTO.totale= this.totale;
			carrelloDTO.prodotti= this.prodotti;
			carrelloDTO.cliente = this.cliente;
			carrelloDTO.quantita= this.quantita;

			return carrelloDTO;
		}
       	
	}
	public Integer getIdCarrello() {
		return idCarrello;
	}
	
	public Double getTotale() {
		return totale;
	}
	
	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}
	
	public ClienteDTO getCliente() {
		return cliente;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	@Override
	public String toString() {
		return "CarrelloDTO [idCarrello=" + idCarrello + ", totale=" + totale + ", prodotti=" + prodotti + ", cliente="
				+ cliente + ", quantita=" + quantita + ", getIdCarrello()=" + getIdCarrello() + ", getTotale()="
				+ getTotale() + ", getProdotti()=" + getProdotti() + ", getCliente()=" + getCliente()
				+ ", getQuantita()=" + getQuantita() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
