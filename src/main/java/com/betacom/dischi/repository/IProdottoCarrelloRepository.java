package com.betacom.dischi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.ProdottoCarrello;

public interface IProdottoCarrelloRepository extends JpaRepository<ProdottoCarrello, Integer>{
	
    Optional<ProdottoCarrello> findByCarrelloAndProdotto(Carrello carrello, Prodotto prodotto);

}
