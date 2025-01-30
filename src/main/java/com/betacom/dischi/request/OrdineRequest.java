package com.betacom.dischi.request;

public class OrdineRequest {

	private Integer idOrdine;
	private String dataOrdine;
	private Double totaleImporto;
	private Integer idCliente;
	@Override
	public String toString() {
		return "OrdineRequest [idOrdine=" + idOrdine + ", dataOrdine=" + dataOrdine + ", totaleImporto=" + totaleImporto
				+ ", idCliente=" + idCliente + "]";
	}
	public Integer getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public Double getTotaleImporto() {
		return totaleImporto;
	}
	public void setTotaleImporto(Double totaleImporto) {
		this.totaleImporto = totaleImporto;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
