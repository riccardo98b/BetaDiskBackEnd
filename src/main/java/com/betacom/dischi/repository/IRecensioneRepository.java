package com.betacom.dischi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Recensione;

public interface IRecensioneRepository extends JpaRepository<Recensione,Integer>  {

	Optional<Recensione> findByClienteAndProdotti(Cliente cliente,Prodotto prodotto) throws CustomException;

	Boolean existsByClienteAndProdotti(Cliente cliente,Prodotto prodotto) throws CustomException;

}
