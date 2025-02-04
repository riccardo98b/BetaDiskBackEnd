package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.controller.CarrelloController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.ProdottoCarrello;
import com.betacom.dischi.repository.ICarrelloRepository;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IProdottoCarrelloRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.services.interfaces.CarrelloService;

@Service
public class CarrelloImpl implements CarrelloService {
	
	@Autowired
	IClienteRepository clienteRepo;
	
	@Autowired
	ICarrelloRepository carrelloRepo;
	
	@Autowired
	IProdottoRepository prodottoRepo;
	
	@Autowired
	IProdottoCarrelloRepository joinRepo;

	@Override
	public Carrello create(CarrelloRequest request) throws CustomException {
		Optional<Cliente> cliente = clienteRepo.findById(request.getIdCliente());
		if (cliente.isEmpty()) {
			throw new CustomException("Cliente inesistente");
		}
		if (cliente.get().getCarrello() != null) {
			throw new CustomException("Carrello esistente, non serve crearlo");
		}
		Carrello carrello = new Carrello();
		carrello.setCliente(cliente.get());
		carrello.setTotale(0.0);
		carrello.setIdCarrello(carrelloRepo.save(carrello).getIdCarrello());
		return carrello;
	}

	@Override
	public void addProdotto(CarrelloRequest request) throws CustomException {
		Optional<Cliente> cliente = clienteRepo.findById(request.getIdCliente());
		if (cliente.isEmpty()) {
			throw new CustomException("Cliente inesistente");
		}
		Carrello carrello = new Carrello();
		if (cliente.get().getCarrello() == null) {
			carrello = create(request);
		} else {
			carrello = cliente.get().getCarrello();
		}
		Prodotto prodotto = prodottoRepo.findById(request.getIdProdotto()).get();
		if (prodotto.getQuantita() < request.getQuantita()) {
			throw new CustomException("Quantità non disponibile");
		}
		Optional<ProdottoCarrello> findRecord = joinRepo.findByCarrelloAndProdotto(carrello, prodotto);
		if (findRecord.isEmpty()) {
			ProdottoCarrello row = new ProdottoCarrello(
					carrello, prodotto, request.getQuantita());
			prodotto.setQuantita(prodotto.getQuantita()-request.getQuantita());
			carrello.getProdotti().add(row);
		} else {
			
		}
		
//		List<Prodotto> prodotti = carrello.getProdotti();
//		Optional<Prodotto> prodotto = prodottoRepo.findById(prodRequest.getIdProdotto());
//		if (prodotto.get().getQuantita() < request.getQuantita()) {
//			throw new CustomException("Quantità in magazzino non disponibile");
//		}
//		if (!prodotti.contains(prodotto.get())){
//			prodotti.add(prodotto.get());
//		}
//		double prezzo = prodotto.get().getPrezzo() * request.getQuantita();
//		carrello.setTotale(carrello.getTotale() + prezzo);
//		carrello.setProdotti(prodotti);
//		carrelloRepo.save(carrello);
	}

	@Override
	public void removeProdotto(CarrelloRequest request) throws CustomException {
//		Optional<Carrello> carrello = carrelloRepo.findById(request.getIdCarrello());
//		if (carrello.isEmpty()) {
//			throw new CustomException("Il carrello non esiste");
//		}
//		List<Prodotto> prodotti = carrello.get().getProdotti();
//		if (prodotti == null) {
//			throw new CustomException("Non ci sono articoli da eliminare");
//		}
//		Optional<Prodotto> prodotto = prodottoRepo.findById(request.getIdProdotto());
//		prodotti.remove(prodotto.get());
//		carrello.get().setTotale(carrello.get().getTotale() - prodotto.get().getPrezzo());
//		carrello.get().setProdotti(prodotti);
//		carrelloRepo.save(carrello.get());
	}

	@Override
	public void delete(CarrelloRequest request) throws CustomException {
		Optional<Carrello> carrello = carrelloRepo.findById(request.getIdCarrello());
		if (carrello.isEmpty()) {
			throw new CustomException("Il carrello non esiste");
		}
		carrelloRepo.delete(carrello.get());
	}

	@Override
	public List<CarrelloDTO> listaProdotti(Integer idCliente) throws CustomException {
		
		return null;
	}

}
