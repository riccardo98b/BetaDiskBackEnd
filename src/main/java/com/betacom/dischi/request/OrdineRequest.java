package com.betacom.dischi.request;

public class OrdineRequest {

	private Integer idOrdine;
	private Boolean spedito;
	private Integer idCliente;
	private String dataRicerca;
	public Integer getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Boolean getSpedito() {
		return spedito;
	}
	public void setSpedito(Boolean spedito) {
		this.spedito = spedito;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		return "OrdineRequest [idOrdine=" + idOrdine + ", spedito=" + spedito + ", idCliente=" + idCliente + "]";
	}
	public String getDataRicerca() {
		return dataRicerca;
	}
	public void setDataRicerca(String dataRicerca) {
		this.dataRicerca = dataRicerca;
	}

	
}
