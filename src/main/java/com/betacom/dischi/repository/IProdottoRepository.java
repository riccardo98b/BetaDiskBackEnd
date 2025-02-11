package com.betacom.dischi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.utilities.enums.Formato;

public interface IProdottoRepository extends JpaRepository<Prodotto, Integer>{

	@Query(name="prodotti.listaFiltrata")
	List<Prodotto> prodottiFiltrati(
			@Param("idProdotto")Integer idProdotto,
			@Param("titolo")String titolo,
			@Param("artista")String artista,
			@Param("genere")String genere,
			@Param("annoPubblicazione")Integer annoPubblicazione,
			@Param("formato") Formato formatoFormattato
			);
}
