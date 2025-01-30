package com.betacom.dischi.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.services.interfaces.ProdottoService;

@Service
public class ProdottoImpl implements ProdottoService{
	
	@Autowired
	Logger log;
	
	@Autowired
	IProdottoRepository prodottoRepository;
	
	

	@Override
	public void create(ProdottoRequest req) throws Exception {
	
		Prodotto prodotto = new Prodotto();
		
		if(req.getFormato() == null)
			throw new CustomException("Inserisci il formato del prodotto");
		
			
		
	}

	@Override
	public List<ProdottoDTO> listAll() {
		
		return null;
	}

	@Override
	public void update(ProdottoRequest req) throws Exception {

		
	}

	@Override
	public void delete(ProdottoRequest req) throws Exception {

		
	}
	

}
