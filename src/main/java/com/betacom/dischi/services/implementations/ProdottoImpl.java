package com.betacom.dischi.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.OrdineDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.services.interfaces.ProdottoService;
import com.betacom.dischi.utilities.Formato;

@Service
public class ProdottoImpl implements ProdottoService{
	
	@Autowired
	Logger log;
	
	@Autowired
	IProdottoRepository prodottoRepository;
	
	

	@Override
	public void create(ProdottoRequest req) throws CustomException {
	
		Prodotto prodotto = new Prodotto();
		
		if(req.getFormato() == null)
			throw new CustomException("Inserisci il formato del prodotto");
		if(req.getTitolo() == null)
			throw new CustomException("Inserisci il titolo del prodotto");
		if(req.getArtista() == null)
			throw new CustomException("Inserisci l'artista del prodotto");
		if(req.getGenere() == null)
			throw new CustomException("Inserisci il genere del prodotto");	
		if(req.getDescrizione() == null)
			throw new CustomException("Inserisci una descrizione del prodotto");
		if(req.getAnnoPubblicazione() == null)
			throw new CustomException("Inserisci l'anno di pubblicazione del prodotto");
		if(req.getPrezzo() == null)
			throw new CustomException("Inserisci il prezzo del prodotto");
		if(req.getQuantita() == null)
			throw new CustomException("Inserisci una quantità disponibile del prodotto");
		
		prodotto.setFormato(Formato.valueOf(req.getFormato()));
		prodotto.setTitolo(req.getTitolo());
		prodotto.setArtista(req.getArtista());
		prodotto.setGenere(req.getGenere());
		prodotto.setDescrizione(req.getDescrizione());
		prodotto.setAnnoPubblicazione(req.getAnnoPubblicazione());
		prodotto.setPrezzo(req.getPrezzo());
		prodotto.setQuantita(req.getQuantita());
		prodotto.setImmagineProdotto(req.getImmagineProdotto());
		
		prodottoRepository.save(prodotto);
	}
	

	@Override
	public List<ProdottoDTO> listAll() {
		
//		List<Prodotto> listaProdotti = prodottoRepository.findAll();
//		
//		return listaProdotti.stream()
//				.map(p -> new ProdottoDTO(
//						p.getIdProdotto(), 
//						p.getTitolo(), 
//						p.getArtista(), 
//						p.getGenere(), 
//						p.getDescrizione(), 
//						p.getAnnoPubblicazione(), 
//						p.getPrezzo(),
//						p.getQuantita(),
//						p.getImmagineProdotto(), 
//						new ArrayList<OrdineDTO>(),
//						new ArrayList<RecensioneDTO>()
//						))
//					.collect(Collectors.toList());
		
		return null;
	}

	@Override
	public void update(ProdottoRequest req) throws Exception {

		
	}

	@Override
	public void delete(ProdottoRequest req) throws Exception {

		
	}
	

}
