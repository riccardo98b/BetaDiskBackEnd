package com.betacom.dischi.models;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer idCarrello;
	
	@Column
	private Double totale;
	
	@Column
	private Integer quantita;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "prodotto_carrelli",
			joinColumns = @JoinColumn(name="id_carrello"),
			inverseJoinColumns = @JoinColumn(name="id_prodotto")
	)
	private List<Prodotto> prodotti;
	
	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	public Integer getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
