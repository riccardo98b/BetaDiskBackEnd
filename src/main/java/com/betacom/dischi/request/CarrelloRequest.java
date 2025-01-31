package com.betacom.dischi.request;

public class CarrelloRequest {

	private Integer idCarrello;
	private Double totale;
	private Integer idCliente;
	private Integer quantita;
	
	@Override
	public String toString() {
		return "CarrelloRequest [idCarrello=" + idCarrello + ", totale=" + totale + ", idCliente=" + idCliente
				+ ", quantita=" + quantita + ", getIdCarrello()=" + getIdCarrello() + ", getTotale()=" + getTotale()
				+ ", getIdCliente()=" + getIdCliente() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
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
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
}
