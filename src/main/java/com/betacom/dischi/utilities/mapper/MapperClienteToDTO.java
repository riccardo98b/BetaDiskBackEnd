package com.betacom.dischi.utilities.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.OrdineDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Ordine;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Utente;
import com.betacom.dischi.models.Wishlist;

public class MapperClienteToDTO {

	// TODO: AGGIORNARE METODI MAP CON IL BUILDER

	// Metodo privato per la mappatura di Cliente a ClienteDTO
	public static ClienteDTO mapClienteToDTO(Cliente cliente) {
		return new ClienteDTO.Builder().setIdCliente(cliente.getIdCliente()).setNome(cliente.getNome())
				.setCognome(cliente.getCognome()).setTelefono(cliente.getTelefono())
				.setImmagineCliente(cliente.getImmagineCliente()).setCarrello(mapCarrello(cliente))
				.setOrdini(mapOrdini(cliente)).setRecensioni(mapRecensioni(cliente)).setUtente(mapUtente(cliente))
				.setWishlist(mapWishlist(cliente)).build();
	}

	public static List<OrdineDTO> mapOrdini(Cliente cliente) {
		if (cliente.getOrdini() == null) {
			return Collections.emptyList();
		}

		return cliente.getOrdini().stream()
				.map(ordine -> new OrdineDTO(ordine.getIdOrdine(), ordine.getDataOrdine(),
						formatDataOrdine(ordine.getDataOrdine()), ordine.getTotaleImporto(), ordine.getSpedito(), null,
						mapProdotti(ordine)))
				.collect(Collectors.toList());
	}

	public static List<ProdottoDTO> mapProdotti(Ordine ordine) {
		if (ordine.getProdotti() == null) {
			return Collections.emptyList();
		}

		return ordine.getProdotti().stream()
				.map(prodotto -> new ProdottoDTO(prodotto.getIdProdotto(), prodotto.getFormato().toString(),
						prodotto.getTitolo(), prodotto.getArtista(), prodotto.getGenere(), prodotto.getDescrizione(),
						prodotto.getAnnoPubblicazione(), prodotto.getPrezzo(), prodotto.getQuantita(),
						prodotto.getImmagineProdotto(), Collections.emptyList(), Collections.emptyList()))
				.collect(Collectors.toList());
	}

	// TODO: DA SISTEMARE
	public static List<RecensioneDTO> mapRecensioni(Cliente cliente) {
		if (cliente.getRecensioni() == null) {
			return Collections.emptyList();
		}

		return cliente.getRecensioni().stream()
				.map(feed -> new RecensioneDTO.Builder().setIdRecensione(feed.getIdRecensione())
						.setDescrizione(feed.getDescrizione()).setStelle(feed.getStelle())
						// .setCliente(null) // non dovrebbe servire
						.setProdotti(null) // da aggiungere,serve Builder
						.build())
				.collect(Collectors.toList());
	}

	// TODO: Serve builder
	public static WishlistDTO mapWishlist(Cliente cliente) {
		if (cliente.getWishlist() == null) {
			return null;
		}
		Wishlist wishlist = cliente.getWishlist();

		return new WishlistDTO(wishlist.getIdWishlist(), getProdottiIds(wishlist), cliente.getIdCliente());
	}

	// TODO: Serve builder
	public static CarrelloDTO mapCarrello(Cliente cliente) {
		if (cliente.getCarrello() == null) {
			return null;
		}
		Carrello carrello = cliente.getCarrello();

		return new CarrelloDTO(carrello.getIdCarrello(), carrello.getTotale(), mapProdotti(carrello), null,
				carrello.getQuantita());
	}

	// TODO :serve builder
	public static List<ProdottoDTO> mapProdotti(Carrello carrello) {
		if (carrello.getProdotti() == null) {
			return Collections.emptyList();
		}

		return carrello.getProdotti().stream()
				.map(prodotto -> new ProdottoDTO(prodotto.getIdProdotto(), prodotto.getFormato().toString(),
						prodotto.getTitolo(), prodotto.getArtista(), prodotto.getGenere(), prodotto.getDescrizione(),
						prodotto.getAnnoPubblicazione(), prodotto.getPrezzo(), prodotto.getQuantita(),
						prodotto.getImmagineProdotto(), Collections.emptyList(), Collections.emptyList()))
				.collect(Collectors.toList());
	}

	// TODO: serve builder
	public static UtenteDTO mapUtente(Cliente cliente) {
		if (cliente.getUtente() == null) {
			return null;
		}
		Utente utente = cliente.getUtente();

		return new UtenteDTO(utente.getIdUtente(), utente.getUsername(), utente.getEmail(), null,
				utente.getRoles().toString(), null);
	}

	// TODO: probabilmente da cancellare
	private static List<Integer> getProdottiIds(Wishlist wishlist) {
		if (wishlist.getProdotti() == null) {
			return Collections.emptyList();
		}

		return wishlist.getProdotti().stream().map(Prodotto::getIdProdotto).collect(Collectors.toList());
	}

	private static String formatDataOrdine(LocalDate dataOrdine) {
		if (dataOrdine != null) {
			return dataOrdine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return null;
	}
}