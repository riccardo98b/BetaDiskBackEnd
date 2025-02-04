package com.betacom.dischi.request;

public class CarrelloRequest {

	private Integer idCarrello;
	private Integer idCliente;
	private Integer idProdotto;
	private Integer quantita;
	public Integer getIdCarrello() {
		return idCarrello;
	}
	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "CarrelloRequest [idCarrello=" + idCarrello + ", idCliente=" + idCliente + ", idProdotto=" + idProdotto
				+ ", quantita=" + quantita + "]";
	}
	
}
