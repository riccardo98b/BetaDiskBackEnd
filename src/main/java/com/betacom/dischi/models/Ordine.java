package com.betacom.dischi.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ordini")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer idOrdine;
	
	@Column(nullable=false)
	private LocalDate dataOrdine;
	
	@Column(nullable=false)
	private Double totaleImporto;
	
	@ManyToOne
	@JoinColumn(name= "id_cliente" , nullable=false)		
	private Cliente cliente;

	@ManyToMany(fetch = FetchType.EAGER)
			@JoinTable(
					name = "prodotto_ordine",
					joinColumns = @JoinColumn(name="id_ordine"),
					inverseJoinColumns = @JoinColumn(name="id_prodotto")
			)
	private List<Prodotto> prodotti;

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

	public Double getTotaleImporto() {
		return totaleImporto;
	}

	public void setTotaleImporto(Double totaleImporto) {
		this.totaleImporto = totaleImporto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	

}
