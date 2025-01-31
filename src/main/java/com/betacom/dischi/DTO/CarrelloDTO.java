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
	public CarrelloDTO() {
		super();
	}
	public CarrelloDTO(Integer idCarrello, Double totale, List<ProdottoDTO> prodotti, ClienteDTO cliente, Integer quantita) {
		super();
		this.idCarrello = idCarrello;
		this.totale = totale;
		this.prodotti = prodotti;
		this.cliente = cliente;
		this.quantita = quantita;
	}
	public Integer getIdCarrello() {
		return idCarrello;
	}
	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoDTO> prodotti) {
		this.prodotti = prodotti;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
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
