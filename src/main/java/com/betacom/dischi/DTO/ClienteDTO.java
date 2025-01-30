package com.betacom.dischi.DTO;

import java.util.List;

import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Ordine;
import com.betacom.dischi.models.Recensione;
import com.betacom.dischi.models.Utente;
import com.betacom.dischi.models.Wishlist;



public class ClienteDTO {
	

	private Integer idCliente;
	

	private String nome;
	

	private String cognome;
	

	private String telefono;
	
	private String immagineCliente;
	
	private List<OrdineDTO> ordini;
	
	private CarrelloDTO carrello;
	
	private UtenteDTO utente;
	
    private WishlistDTO wishlist;
	
    private List<RecensioneDTO> recensioni;
    
    public ClienteDTO() {}

	public ClienteDTO(Integer idCliente, String nome, String cognome, String telefono, String immagineCliente,
			List<OrdineDTO> ordini, CarrelloDTO carrello, UtenteDTO utente, WishlistDTO wishlist,
			List<RecensioneDTO> recensioni) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.immagineCliente = immagineCliente;
		this.ordini = ordini;
		this.carrello = carrello;
		this.utente = utente;
		this.wishlist = wishlist;
		this.recensioni = recensioni;
	}

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

	public List<OrdineDTO> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineDTO> ordini) {
		this.ordini = ordini;
	}

	public CarrelloDTO getCarrello() {
		return carrello;
	}

	public void setCarrello(CarrelloDTO carrello) {
		this.carrello = carrello;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public WishlistDTO getWishlist() {
		return wishlist;
	}

	public void setWishlist(WishlistDTO wishlist) {
		this.wishlist = wishlist;
	}

	public List<RecensioneDTO> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<RecensioneDTO> recensioni) {
		this.recensioni = recensioni;
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nome=" + nome + ", cognome=" + cognome + ", telefono="
				+ telefono + ", immagineCliente=" + immagineCliente + ", ordini=" + ordini + ", carrello=" + carrello
				+ ", recensioni=" + recensioni + "]";
	}
    
    
    
   
}
