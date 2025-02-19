package com.betacom.dischi.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class RecensioneDTO {

	private Integer idRecensione;
	private String descrizione;
	private Integer stelle;
	private LocalDate dataCreazione;
	private ClienteDTO cliente;
	private ProdottoDTO prodotto;
	
	private RecensioneDTO() {}

    public static class Builder{
    	private Integer idRecensione;
    	private String descrizione;
    	private Integer stelle;
    	private LocalDate dataCreazione;
    	private ClienteDTO cliente;
    	private ProdottoDTO prodotto;

		public Builder idRecensione(Integer idRecensione) {
			this.idRecensione = idRecensione;
			return this;
		}

		public Builder descrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public Builder stelle(Integer stelle) {
			this.stelle = stelle;
			return this;
		}

		public Builder cliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}

		public Builder prodotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
			return this;
		}
		
		public Builder dataCreazione(LocalDate dataCreazione) {
			this.dataCreazione = dataCreazione;
			return this;
		}

		public RecensioneDTO build() {
			RecensioneDTO recDTO = new RecensioneDTO();
			recDTO.idRecensione = this.idRecensione;
			recDTO.descrizione = this.descrizione;
			recDTO.stelle = this.stelle;
			recDTO.dataCreazione=this.dataCreazione;
			recDTO.cliente = this.cliente;
			recDTO.prodotto= this.prodotto;
		
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
	public ProdottoDTO getProdotto() {
		return prodotto;
	}
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataCreazione, descrizione, idRecensione, prodotto, stelle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecensioneDTO other = (RecensioneDTO) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataCreazione, other.dataCreazione)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(idRecensione, other.idRecensione)
				&& Objects.equals(prodotto, other.prodotto) && Objects.equals(stelle, other.stelle);
	}

	
	
}
