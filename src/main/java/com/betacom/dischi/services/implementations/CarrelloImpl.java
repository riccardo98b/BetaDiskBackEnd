package com.betacom.dischi.services.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.DTO.ProdottoCarrelloDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
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
import com.betacom.dischi.services.interfaces.CarrelloService;

import jakarta.transaction.Transactional;

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
	@Transactional
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
			if (carrello.getProdotti()== null) {
				carrello.setProdotti(new ArrayList<ProdottoCarrello>());
			}
			carrello.getProdotti().add(row);
		} else {
			ProdottoCarrello row = findRecord.get();
			row.setQuantita(row.getQuantita() + request.getQuantita());
		}
		prodotto.setQuantita(prodotto.getQuantita() - request.getQuantita());
		double prezzo = prodotto.getPrezzo() * request.getQuantita();
		carrello.setTotale(carrello.getTotale() + prezzo);
		prodottoRepo.save(prodotto);
		carrelloRepo.save(carrello);
	}

	@Override
	@Transactional
	public void removeProdotto(CarrelloRequest request) throws CustomException {
		Optional<Cliente> cliente = clienteRepo.findById(request.getIdCliente());
		if (cliente.isEmpty()) {
			throw new CustomException("Cliente inesistente");
		}
		Carrello carrello = cliente.get().getCarrello();

		Prodotto prodotto = prodottoRepo.findById(request.getIdProdotto()).get();
		Optional<ProdottoCarrello> findRecord = joinRepo.findByCarrelloAndProdotto(carrello, prodotto);
		if (findRecord.isPresent()) {
			ProdottoCarrello row = findRecord.get();
			prodotto.setQuantita(prodotto.getQuantita() + request.getQuantita());
			double prezzo = prodotto.getPrezzo() * request.getQuantita();
			carrello.setTotale(carrello.getTotale() - prezzo);
			if (row.getQuantita() > request.getQuantita()) {
				row.setQuantita(row.getQuantita() - request.getQuantita());
				joinRepo.save(row);
			} else {
				carrello.getProdotti().remove(row);
				joinRepo.delete(row);
			}
			prodottoRepo.save(prodotto);
			carrelloRepo.save(carrello);
		} else {
			throw new CustomException("Prodotto non presente nel carrello");
		}
	}

	@Override
	@Transactional
	public void delete(CarrelloRequest request) throws CustomException {
		Optional<Cliente> cliente = clienteRepo.findById(request.getIdCliente());
		if (cliente.isEmpty()) {
			throw new CustomException("Cliente inesistente");
		}
		Carrello carrello = new Carrello();
		if (cliente.get().getCarrello() == null) {
			throw new CustomException("Il carrello è vuoto");
		} else {
			carrello = cliente.get().getCarrello();
		}
		cliente.get().setCarrello(null);
		clienteRepo.save(cliente.get());
		for (ProdottoCarrello row : carrello.getProdotti()) {
			Prodotto prodotto = row.getProdotto();
			prodotto.setQuantita(prodotto.getQuantita() + row.getQuantita());
			prodottoRepo.save(prodotto);
		}
		carrelloRepo.delete(carrello);
	}

	@Override
	public CarrelloDTO listaProdotti(Integer id) throws CustomException {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		if (cliente.isEmpty()) {
			throw new CustomException("Cliente inesistente");
		}
		Carrello carrello = cliente.get().getCarrello();
		List<ProdottoCarrello> listaProdotti = carrello.getProdotti();
		return new CarrelloDTO.Builder()
				.totale(carrello.getTotale())
				.prodotti(listaProdotti.stream()
							.map(prodotto -> new ProdottoCarrelloDTO.Builder()
												.quantita(prodotto.getQuantita())
												.prodotto(new ProdottoDTO.Builder()
														.idProdotto(prodotto.getProdotto().getIdProdotto())
														.titolo(prodotto.getProdotto().getTitolo())
														.artista(prodotto.getProdotto().getArtista())
														.prezzo(prodotto.getProdotto().getPrezzo())
														.immagineProdotto(prodotto.getProdotto().getImmagineProdotto())
												.build())
								.build()
						).toList())
				.build();
	}

//	@Override
//	public List<CarrelloDTO> listaProdotti(Integer idCliente) throws CustomException {
//		List<Cliente> listaCompleta = clienteRepo.findAll();
//		return listaCompleta.stream().map(cliente -> new CarrelloDTO.Builder().build()).toList();
//	}
}
