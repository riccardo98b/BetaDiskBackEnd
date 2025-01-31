package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.repository.ICarrelloRepository;
import com.betacom.dischi.repository.IClienteRepository;
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
		carrello.setIdCarrello(carrelloRepo.save(carrello).getIdCarrello());
		return carrello;
	}

	@Override
	public void addProdotto(CarrelloRequest request, ProdottoRequest prodRequest) throws CustomException {
		Optional<Carrello> carrelloOpt = carrelloRepo.findById(request.getIdCarrello());
		Carrello carrello = new Carrello();
		if (carrelloOpt.isEmpty()) {
			carrello = create(request);
		}
		List<Prodotto> prodotti = carrello.getProdotti();
		Optional<Prodotto> prodotto = prodottoRepo.findById(prodRequest.getIdProdotto());
		prodotti.add(prodotto.get());
		carrello.setTotale(carrello.getTotale() + prodotto.get().getPrezzo());
		carrello.setProdotti(prodotti);
		carrelloRepo.save(carrello);
	}

	@Override
	public void removeProdotto(CarrelloRequest request, ProdottoRequest prodRequest) throws CustomException {
		Optional<Carrello> carrello = carrelloRepo.findById(request.getIdCarrello());
		if (carrello.isEmpty()) {
			throw new CustomException("Il carrello non esiste");
		}
		List<Prodotto> prodotti = carrello.get().getProdotti();
		if (prodotti == null) {
			throw new CustomException("Non ci sono articoli da eliminare");
		}
		Optional<Prodotto> prodotto = prodottoRepo.findById(prodRequest.getIdProdotto());
		prodotti.remove(prodotto.get());
		carrello.get().setTotale(carrello.get().getTotale() - prodotto.get().getPrezzo());
		carrello.get().setProdotti(prodotti);
		carrelloRepo.save(carrello.get());
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
