package com.betacom.dischi.DTO;

import java.time.LocalDate;
import java.util.List;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.models.ProdottoOrdine;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class OrdineDTO {

	private Integer idOrdine;
	private LocalDate dataOrdine;
	private Double totaleImporto;
	private Boolean spedito;
	private ClienteDTO cliente;
	private List<ProdottoOrdineDTO> prodotti;
	private OrdineDTO() {
		super();
	}
	public Integer getIdOrdine() {
		return idOrdine;
	}
	public LocalDate getDataOrdine() {
		return dataOrdine;
	}
	public Double getTotaleImporto() {
		return totaleImporto;
	}
	public Boolean getSpedito() {
		return spedito;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public List<ProdottoOrdineDTO> getProdotti() {
		return prodotti;
	}
	public static class Builder {
		private Integer idOrdine;
		private LocalDate dataOrdine;
		private Double totaleImporto;
		private Boolean spedito;
		private ClienteDTO cliente;
		private List<ProdottoOrdineDTO> prodotti;
		public Builder() {}
		public OrdineDTO build() {
			OrdineDTO dto = new OrdineDTO();
			dto.idOrdine = this.idOrdine;
			dto.dataOrdine = this.dataOrdine;
			dto.totaleImporto = this.totaleImporto;
			dto.spedito = this.spedito;
			dto.cliente = this.cliente;
			dto.prodotti = this.prodotti;
			return dto;
		}
		public Builder idOrdine(Integer idOrdine) {
			this.idOrdine = idOrdine;
			return this;
		}
		public Builder dataOrdine(LocalDate dataOrdine) {
			this.dataOrdine = dataOrdine;
			return this;
		}
		public Builder totaleImporto(Double totaleImporto) {
			this.totaleImporto = totaleImporto;
			return this;
		}
		public Builder spedito(Boolean spedito) {
			this.spedito = spedito;
			return this;
		}
		public Builder cliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}
		public Builder prodotti(List<ProdottoOrdineDTO> prodotti) {
			this.prodotti = prodotti;
			return this;
		}
		
	}

	
}
