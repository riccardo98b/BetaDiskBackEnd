package com.betacom.dischi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ProdottoOrdine {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_ordine")
	private Ordine ordine;

	@ManyToOne
	@JoinColumn(name = "id_prodotto")
	private Prodotto prodotto;

	private Integer quantita;
	private Double prezzoAcquisto;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public Double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public ProdottoOrdine() {
		super();
	}
	public ProdottoOrdine(Ordine ordine, Prodotto prodotto, Integer quantita, Double prezzoAcquisto) {
		super();
		this.ordine = ordine;
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.prezzoAcquisto = prezzoAcquisto;
	}
	
}
