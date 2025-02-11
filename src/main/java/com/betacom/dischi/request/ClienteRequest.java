package com.betacom.dischi.request;


public class ClienteRequest {
	

	private Integer idCliente;
	
	private String nome;
	
	private String cognome;
	
	private String telefono;
	
	private String immagineCliente;
	
    private String via;
	
	private String cap;
	
	private String provincia;
	
	private String comune;


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getImmagineCliente() {
		return immagineCliente;
	}

	public void setImmagineCliente(String immagineCliente) {
		this.immagineCliente = immagineCliente;
	}
	
	

	public String getVia() {
		return via;
	}

	public String getCap() {
		return cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	@Override
	public String toString() {
		return "ClienteRequest [idCliente=" + idCliente + ", nome=" + nome + ", cognome=" + cognome + ", telefono="
				+ telefono + ", immagineCliente=" + immagineCliente + "]";
	}


    

}
