package com.betacom.dischi.DTO;

import java.util.List;


public class ClienteDTO { // APPLICO IL PATTERN BUILDER
	// 1) ATTRIBUTI DTO
	private Integer idCliente;
	
	private String nome;
	
	private String cognome;

	private String telefono;
	
	private String immagineCliente;
		
	private String via;
	
	private String cap;
	
	private String provincia;
	
	private String comune;
	
	private List<OrdineDTO> ordini;
	
	private CarrelloDTO carrello;
	
	private UtenteDTO utente;
	
    private WishlistDTO wishlist;
	
    private List<RecensioneDTO> recensioni;
    
    
   // 2) COSTRUTTORE PRIVATO PER DTO(NO ISTANZE AD DI FUORI DI QUESTA CLASSE)
    private ClienteDTO() {}
    
    // INNER CLASS BUILDER PER CLIENTEDTO
    public static class Builder{
    	// 1)  riprendo le variabili di oggetto DTO
    	private Integer idCliente;
    	

    	private String nome;
    	

    	private String cognome;
    	

    	private String telefono;
    	
    	private String immagineCliente;
    	
    	private String via;
    	
    	private String cap;
    	
    	private String provincia;
    	
    	private String comune;
    
    	
    	private List<OrdineDTO> ordini;
    	
    	private CarrelloDTO carrello;
    	
    	private UtenteDTO utente;
    	
        private WishlistDTO wishlist;
    	
        private List<RecensioneDTO> recensioni;

        //2 FAI SOLO I SET, questi devono tornare tutti Builder(il niome che abbaimo dato a innerclass che applica il pattern)
		public Builder setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
			return this;
		}

		public Builder setNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder setCognome(String cognome) {
			this.cognome = cognome;
			return this;
		}

		public Builder setTelefono(String telefono) {
			this.telefono = telefono;
			return this;
		}

		public Builder setImmagineCliente(String immagineCliente) {
			this.immagineCliente = immagineCliente;
			return this;
		}

		public Builder setOrdini(List<OrdineDTO> ordini) {
			this.ordini = ordini;
			return this;
		}

		public Builder setCarrello(CarrelloDTO carrello) {
			this.carrello = carrello;
			return this;

		}

		public Builder setUtente(UtenteDTO utente) {
			this.utente = utente;
			return this;

		}

		public Builder setWishlist(WishlistDTO wishlist) {
			this.wishlist = wishlist;
			return this;

		}

		public Builder setRecensioni(List<RecensioneDTO> recensioni) {
			this.recensioni = recensioni;
			return this;

		}
		
		
	
		public Builder setVia(String via) {
			this.via = via;
			return this;
		}

		public Builder setCap(String cap) {
			this.cap = cap;
			return this;
		}

		public Builder setProvincia(String provincia) {
			this.provincia = provincia;
			return this;
		}

		public Builder setComune(String comune) {
			this.comune = comune;
			return this;
		}

		//3) ORA FAI UN METODO BUILD PER COSTRUIRE IL DTO E RITORNARLO
		public ClienteDTO build() {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.idCliente = this.idCliente;
			clienteDTO.nome= this.nome;
			clienteDTO.cognome= this.cognome;
			clienteDTO.telefono = this.telefono;
			clienteDTO.immagineCliente= this.immagineCliente;
			clienteDTO.ordini = this.ordini;
			clienteDTO.carrello= this.carrello;
			clienteDTO.utente= this.utente;
			clienteDTO.wishlist= this.wishlist;
			clienteDTO.recensioni= this.recensioni;
			clienteDTO.cap= this.cap;
			clienteDTO.comune= this.comune;
			clienteDTO.provincia=this.provincia;
			clienteDTO.via= this.via;
			return clienteDTO;
		}
        
     
        
        
    }

    // 3 get per l'oggetto DTO, immutabile all'esterno , è consentrita solo la visualzizzazione
	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getImmagineCliente() {
		return immagineCliente;
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

	public List<OrdineDTO> getOrdini() {
		return ordini;
	}

	public CarrelloDTO getCarrello() {
		return carrello;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public WishlistDTO getWishlist() {
		return wishlist;
	}

	public List<RecensioneDTO> getRecensioni() {
		return recensioni;
	}
    
    
}
