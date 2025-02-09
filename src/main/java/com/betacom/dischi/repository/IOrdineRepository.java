package com.betacom.dischi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Ordine;
import com.betacom.dischi.models.Prodotto;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer> {

	Boolean existsByClienteAndProdotti(Cliente cliente, Prodotto prodotto);

}
