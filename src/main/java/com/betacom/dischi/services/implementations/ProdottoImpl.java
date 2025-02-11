package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.services.interfaces.ProdottoService;
import com.betacom.dischi.utilities.enums.Formato;

import jakarta.transaction.Transactional;

import static com.betacom.dischi.utilities.Utility.validazioneValoriProdotto;
import static com.betacom.dischi.utilities.Utility.formatoToString;

@Service
public class ProdottoImpl implements ProdottoService{
	
	@Autowired
	Logger log;
	
	@Autowired
	IProdottoRepository prodottoRepository;
	
	

	@Override
	public void create(ProdottoRequest req) throws CustomException {
	
		Prodotto prodotto = new Prodotto();
		
		validazioneValoriProdotto(req);
		
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
	public void update(ProdottoRequest req) throws CustomException {
		
		Optional<Prodotto> prodotto = prodottoRepository.findById(req.getIdProdotto());
		if(prodotto.isEmpty())
			throw new CustomException("Prodotto non trovato");
		
		Prodotto p = prodotto.get();
		
		validazioneValoriProdotto(req);
		
		p.setFormato(Formato.valueOf(req.getFormato()));
		p.setTitolo(req.getTitolo());
		p.setArtista(req.getArtista());
		p.setGenere(req.getGenere());
		p.setDescrizione(req.getDescrizione());
		p.setAnnoPubblicazione(req.getAnnoPubblicazione());
		p.setPrezzo(req.getPrezzo());
		p.setQuantita(req.getQuantita());
		p.setImmagineProdotto(req.getImmagineProdotto());
		
		prodottoRepository.save(p);
		
	}
	

	@Transactional(rollbackOn = CustomException.class)
	@Override
	public void delete(ProdottoRequest req) throws CustomException {
		Optional<Prodotto> prodotto = prodottoRepository.findById(req.getIdProdotto());
		if(prodotto.isEmpty())
			throw new CustomException("Prodotto non trovato");
		
		Prodotto p = prodotto.get();
		prodottoRepository.delete(p);
		
	}


	@Override
	public List<ProdottoDTO> listAll(Integer idProdotto, String titolo, String artista, String genere,
			Integer annoPubblicazione, String formato) throws Exception {
		
	    Formato formatoEnum = formatoToString(formato);
		List<Prodotto> listaFiltrata = prodottoRepository.prodottiFiltrati(idProdotto,titolo, artista, genere, annoPubblicazione, formatoEnum);
		
		return listaFiltrata.stream()
				.map(p -> new ProdottoDTO.Builder()
						.idProdotto(p.getIdProdotto())
						.formato(p.getFormato().toString())
						.titolo(p.getTitolo())
						.artista(p.getArtista())
						.genere(p.getGenere())
						.descrizione(p.getDescrizione())
						.annoPubblicazione(p.getAnnoPubblicazione())
						.prezzo(p.getPrezzo())
						.immagineProdotto(p.getImmagineProdotto())
						.quantita(p.getQuantita()) 
						.build())
				.collect(Collectors.toList());
		
		
	}
}
	