package com.betacom.dischi.request;


public class RecensioneRequest {


	private Integer idRecensione;
	

	private String descrizione;
	
	private Integer stelle;

	public Integer getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(Integer idRecensione) {
		this.idRecensione = idRecensione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getStelle() {
		return stelle;
	}

	public void setStelle(Integer stelle) {
		this.stelle = stelle;
	}

	@Override
	public String toString() {
		return "RecensioneRequest [idRecensione=" + idRecensione + ", descrizione=" + descrizione + ", stelle=" + stelle
				+ "]";
	}
	
	
	
	
}
