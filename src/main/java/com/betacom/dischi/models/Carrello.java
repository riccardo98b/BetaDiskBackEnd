package com.betacom.dischi.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer idCarrello;
	
	@OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdottoCarrello> prodotti;

	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	public Integer getIdCarrello() {
		return idCarrello;
	}
	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}
	public List<ProdottoCarrello> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoCarrello> prodotti) {
		this.prodotti = prodotti;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
