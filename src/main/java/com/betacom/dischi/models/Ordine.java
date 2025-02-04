package com.betacom.dischi.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ordini")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer idOrdine;
	
	@ManyToOne
	@JoinColumn(name= "id_cliente" , nullable=false)		
	private Cliente cliente;

	@OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdottoOrdine> prodotti;

	@Column(nullable=false)
	private LocalDate dataOrdine;
	
	@Column(nullable=false)
	private Double totaleImporto;
	
	@Column
	private Boolean spedito;

	public Integer getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ProdottoOrdine> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoOrdine> prodotti) {
		this.prodotti = prodotti;
	}
	public LocalDate getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public Double getTotaleImporto() {
		return totaleImporto;
	}
	public void setTotaleImporto(Double totaleImporto) {
		this.totaleImporto = totaleImporto;
	}
	public Boolean getSpedito() {
		return spedito;
	}
	public void setSpedito(Boolean spedito) {
		this.spedito = spedito;
	}
	
	


}
