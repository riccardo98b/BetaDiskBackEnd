package com.betacom.dischi.DTO;

import java.util.List;

import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class RecensioneDTO {

	private Integer idRecensione;
	

	private String descrizione;
	

	private Integer stelle;
	
	
	private ClienteDTO cliente;
	

	private List<ProdottoDTO> prodotti;
	
	
	public RecensioneDTO() {}


	public RecensioneDTO(Integer idRecensione, String descrizione, Integer stelle, ClienteDTO cliente,
			List<ProdottoDTO> prodotti) {
		super();
		this.idRecensione = idRecensione;
		this.descrizione = descrizione;
		this.stelle = stelle;
		this.cliente = cliente;
		this.prodotti = prodotti;
	}


	@Override
	public String toString() {
		return "RecensioneDTO [idRecensione=" + idRecensione + ", descrizione=" + descrizione + ", stelle=" + stelle
				+ ", cliente=" + cliente + "]";
	}
	
	
	
	
	
}
