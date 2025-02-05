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
	
	
	private RecensioneDTO() {}

    public static class Builder{
    	private Integer idRecensione;
    	

    	private String descrizione;
    	

    	private Integer stelle;
    	
    	
    	private ClienteDTO cliente;
    	

    	private List<ProdottoDTO> prodotti;


	

		public Builder setIdRecensione(Integer idRecensione) {
			this.idRecensione = idRecensione;
			return this;
		}


		public Builder setDescrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}


		public Builder setStelle(Integer stelle) {
			this.stelle = stelle;
			return this;
		}


		public Builder setCliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}


		public Builder setProdotti(List<ProdottoDTO> prodotti) {
			this.prodotti = prodotti;
			return this;
		}
		
		
		public RecensioneDTO build() {
			RecensioneDTO recDTO = new RecensioneDTO();
			recDTO.idRecensione = this.idRecensione;
			recDTO.descrizione = this.descrizione;
			recDTO.stelle = this.stelle;
			recDTO.cliente = this.cliente;
			recDTO.prodotti= this.prodotti;
			return recDTO;
		}
    	
    	
    }





	@Override
	public String toString() {
		return "RecensioneDTO [idRecensione=" + idRecensione + ", descrizione=" + descrizione + ", stelle=" + stelle
				+ ", cliente=" + cliente + "]";
	}


	public Integer getIdRecensione() {
		return idRecensione;
	}





	public String getDescrizione() {
		return descrizione;
	}




	public Integer getStelle() {
		return stelle;
	}


	


	public ClienteDTO getCliente() {
		return cliente;
	}


	


	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}



	
	
	
}
