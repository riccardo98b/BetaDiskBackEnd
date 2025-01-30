package com.betacom.dischi.DTO;

import java.time.LocalDate;
import java.util.List;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.ProdottoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class OrdineDTO {

	private Integer idOrdine;
	private LocalDate dataOrdine;
	private String dataFormattata;
	private Double totaleImporto;
	private ClienteDTO cliente;
	private List<ProdottoDTO> prodotti;
	public OrdineDTO() {
		super();
	}
	public OrdineDTO(Integer idOrdine, LocalDate dataOrdine, String dataFormattata, Double totaleImporto,
			ClienteDTO cliente, List<ProdottoDTO> prodotti) {
		super();
		this.idOrdine = idOrdine;
		this.dataOrdine = dataOrdine;
		this.dataFormattata = dataFormattata;
		this.totaleImporto = totaleImporto;
		this.cliente = cliente;
		this.prodotti = prodotti;
	}
	public Integer getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	public LocalDate getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public String getDataFormattata() {
		return dataFormattata;
	}
	public void setDataFormattata(String dataFormattata) {
		this.dataFormattata = dataFormattata;
	}
	public Double getTotaleImporto() {
		return totaleImporto;
	}
	public void setTotaleImporto(Double totaleImporto) {
		this.totaleImporto = totaleImporto;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoDTO> prodotti) {
		this.prodotti = prodotti;
	}
	@Override
	public String toString() {
		return "OrdineDTO [idOrdine=" + idOrdine + ", dataOrdine=" + dataOrdine + ", dataFormattata=" + dataFormattata
				+ ", totaleImporto=" + totaleImporto + ", cliente=" + cliente + "]";
	}
	
	
}
