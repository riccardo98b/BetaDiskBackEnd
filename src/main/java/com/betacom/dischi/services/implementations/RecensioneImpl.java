package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.exception.cliente.ClienteNotFoundException;
import com.betacom.dischi.exception.recensione.CannotReviewProdottoNotBought;
import com.betacom.dischi.exception.recensione.RecensioneAlreadyDoneException;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Recensione;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IOrdineRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.repository.IRecensioneRepository;
import com.betacom.dischi.request.RecensioneRequest;
import com.betacom.dischi.services.interfaces.RecensioneService;
import com.betacom.dischi.utilities.mapper.MapperClienteToDTO;

@Service
public class RecensioneImpl implements RecensioneService {
	
	@Autowired
	Logger log;
	@Autowired
	IRecensioneRepository recensioneRepo;
	@Autowired
	IClienteRepository clienteRepo;
	@Autowired
	IProdottoRepository prodottoRepo;
	@Autowired
	IOrdineRepository ordineRepo;

	@Override
	public List<RecensioneDTO> listAll() {
	    List<Recensione> listaRecensioni = recensioneRepo.findAll();
	    return listaRecensioni.stream()
	            .map(rece -> new RecensioneDTO.Builder()
	                    .setIdRecensione(rece.getIdRecensione())
	                    .setDescrizione(rece.getDescrizione())
	                    .setStelle(rece.getStelle())
	                    .setCliente(MapperClienteToDTO.mapClienteToDTO(rece.getCliente()))  
	                    // MANCA LA DATA RECENSIONE
	                    .setProdotti(null) 
	                    .build())  
	            .collect(Collectors.toList());
	}
	
	@Override
	public void create(RecensioneRequest req) throws Exception,ClienteNotFoundException {
		log.debug("Create recensione: "+req);
		Recensione recensione = new Recensione();
		checkAndSetFields(recensione,req);
		recensioneRepo.save(recensione);
		log.debug("Recensione creata con ID: "+recensione.getIdRecensione() + " e dettagli: " + recensione);
	}

	@Override
	public void update(RecensioneRequest req) throws Exception {
		
	}

	@Override
	public void delete(RecensioneRequest req) throws Exception {
		
	}
	
	private Recensione checkAndSetFields(Recensione recensione,RecensioneRequest req) throws CustomException {
		Cliente cliente = clienteRepo.findById(req.getIdCliente())
				.orElseThrow(() -> new CustomException("cliente con id "+req.getIdCliente()+" non trovato"));
		Prodotto prodotto = prodottoRepo.findById(req.getIdProdotto())
				.orElseThrow(() -> new CustomException("prodotto con id "+req.getIdProdotto()+" non trovato"));
		checkIfRecensioneAlreadyExists(cliente,prodotto);
		checkIfClienteAlreadyBoughtProduct(cliente,prodotto);
	    checkIfRecensioneRequestHasValidParameters(req);
		recensione.setStelle(req.getStelle());
		recensione.setDescrizione(req.getDescrizione());
		recensione.setCliente(cliente);
	    recensione.setProdotti(List.of(prodotto));  // unico prodotto 
		return recensione;
	}
	private void checkIfRecensioneAlreadyExists(Cliente cliente, Prodotto prodotto) throws CustomException {
		Boolean recensioneEsistente = recensioneRepo.existsByClienteAndProdotti(cliente, prodotto);
		if(recensioneEsistente) 
			throw new RecensioneAlreadyDoneException();
	}
	
	private void checkIfClienteAlreadyBoughtProduct(Cliente cliente, Prodotto prodotto) throws CustomException {
		Boolean haAcquistato = ordineRepo.existsByClienteAndProdotti(cliente, prodotto);
		if(!haAcquistato)
			throw new CannotReviewProdottoNotBought();
		}
	
	public void checkIfRecensioneRequestHasValidParameters(RecensioneRequest req) throws CustomException {
		if(req.getDescrizione() == null || req.getDescrizione().isBlank()) {
			throw new CustomException("La descrizione non può essere nulla o vuota");
		}
		if(req.getStelle() == null) {
			throw new CustomException("Il campo numero di stelle non può essere nullo o vuoto");
		}
		if(req.getStelle() < 1 || req.getStelle() > 5) {
			throw new CustomException("Il numero di stelle deve essere un valore tra 1 e 5");
		}
	}
	@Override
	public RecensioneDTO listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
