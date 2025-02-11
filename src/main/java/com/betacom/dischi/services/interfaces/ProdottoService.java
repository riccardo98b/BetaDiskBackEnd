package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.request.ProdottoRequest;

public interface ProdottoService {

	void create(ProdottoRequest req)throws Exception;
	
	void update(ProdottoRequest req)throws Exception;
	
	void delete(ProdottoRequest req)throws Exception;
	
	List<ProdottoDTO> listAll(Integer idProdotto, String titolo, String artista, String genere, Integer annoPubblicazione, String formato) throws Exception;
}
