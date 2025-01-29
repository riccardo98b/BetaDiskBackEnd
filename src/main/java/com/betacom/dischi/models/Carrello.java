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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "prodotto_carrelli",
			joinColumns = @JoinColumn(name="id_carrello"),
			inverseJoinColumns = @JoinColumn(name="id_prodotto")
	)
	private List<Prodotto> prodotti;
	
	@OneToOne(mappedBy = "carrello")
	private Cliente cliente;
	

}
