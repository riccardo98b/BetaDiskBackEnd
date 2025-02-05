package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Recensione;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.repository.IRecensioneRepository;
import com.betacom.dischi.request.RecensioneRequest;
import com.betacom.dischi.services.interfaces.RecensioneService;

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

	@Override
	public List<RecensioneDTO> listAll() {
	    // Recuperiamo tutte le recensioni
	    List<Recensione> listaRecensioni = recensioneRepo.findAll();

	    return listaRecensioni.stream()
	            .map(rece -> new RecensioneDTO.Builder()
	                    .setIdRecensione(rece.getIdRecensione())
	                    .setDescrizione(rece.getDescrizione())
	                    .setStelle(rece.getStelle())
	                    .setCliente(convertToClienteDTO(rece.getCliente()))  // Convertiamo il cliente in ClienteDTO
	                    .setProdotti(null) // Impostiamo il campo prodotti a null
	                    .build())  // Chiudiamo correttamente la costruzione del DTO
	            .collect(Collectors.toList());
	}
	private ClienteDTO convertToClienteDTO(Cliente cliente) {
	    return new ClienteDTO.Builder()
	            .setIdCliente(cliente.getIdCliente())
	            .setNome(cliente.getNome())
	            .setCognome(cliente.getCognome())
	            .setTelefono(cliente.getTelefono())
	            .setImmagineCliente(cliente.getImmagineCliente())
	            // TODO: da decidere gli altri campi
	            .build();
	}

//	// Metodo per convertire una lista di prodotti in lista di ProdottoDTO
//	private List<ProdottoDTO> convertToProdottoDTO(List<Prodotto> prodotti) {
//	    return prodotti.stream()
//	            .map(prodotto -> new ProdottoDTO.Builder()
//	                    .setIdProdotto(prodotto.getIdProdotto())
//	                    .setNome(prodotto.getNome())
//	                    .setDescrizione(prodotto.getDescrizione())
//	                    .setPrezzo(prodotto.getPrezzo())
//	                    .build())
//	            .collect(Collectors.toList());
//	}

	@Override
	public void create(RecensioneRequest req) throws Exception {
		log.debug("Create recensione: "+req);
		Recensione recensione = new Recensione();
		recensione = checkAndSetFields(req);
		recensioneRepo.save(recensione);
		log.debug("Recensione creata con ID: "+recensione.getIdRecensione() + " e dettagli: " + recensione);
		
		
	}

	@Override
	public void update(RecensioneRequest req) throws Exception {
		
	}

	@Override
	public void delete(RecensioneRequest req) throws Exception {
		
	}
	
	private Recensione checkAndSetFields(RecensioneRequest req) throws CustomException {
		Recensione recensione = new Recensione();
		Cliente cliente = clienteRepo.findById(req.getIdCliente())
				.orElseThrow(() -> new CustomException("cliente con id "+req.getIdCliente()+" non trovato"));
		Prodotto prodotto = prodottoRepo.findById(req.getIdProdotto())
				.orElseThrow(() -> new CustomException("prodotto con id "+req.getIdProdotto()+" non trovato"));
		
		checkIfRecensioneAlreadyExists(cliente,prodotto);
		if(req.getDescrizione() == null || req.getDescrizione().isBlank()) {
			throw new CustomException("La descrizione non può essere nulla o vuota");
		}
		if(req.getStelle() == null) {
			throw new CustomException("Il campo numero di stelle non può essere nullo o vuoto");
		}
		if(req.getStelle() < 1 || req.getStelle() > 5) {
			throw new CustomException("Il numero di stelle deve essere un valore tra 1 e 5");
		}
		
		recensione.setStelle(req.getStelle());
		recensione.setDescrizione(req.getDescrizione());
		recensione.setCliente(cliente);
	    recensione.setProdotti(List.of(prodotto));  // unico prodotto 
		return recensione;
	}
	private void checkIfRecensioneAlreadyExists(Cliente cliente, Prodotto prodotto) throws CustomException {
		Optional<Recensione> recensioneEsistente = recensioneRepo.findByClienteAndProdotti(cliente,prodotto);
	    if(recensioneEsistente.isPresent()) {
			throw new CustomException("Il cliente ha già lasciato una recensione per questo prodotto ");

	    }
	}
	@Override
	public RecensioneDTO listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
