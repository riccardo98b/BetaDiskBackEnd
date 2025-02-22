package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.utilities.enums.Formato;


public interface ProdottoService {

	void create(ProdottoRequest req)throws CustomException;
	
	void update(ProdottoRequest req)throws CustomException;
	
	void delete(ProdottoRequest req)throws CustomException;
	
	List<ProdottoDTO> listAll(Integer idProdotto, String titolo, String artista, String genere,
			Integer annoPubblicazione) throws CustomException;
	
	List<ProdottoDTO> listPerFormato(Formato formato) throws CustomException;
	
	List<ProdottoDTO> topTenProdotti() throws CustomException;
	
	List<Formato> listaDeiFormati() throws CustomException;
}
