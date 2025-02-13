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
		return new ClienteDTO.Builder()
				.setIdCliente(cliente.getIdCliente())
				.setNome(cliente.getNome())
				.setCognome(cliente.getCognome())
				.setTelefono(cliente.getTelefono())
				.setImmagineCliente(cliente.getImmagineCliente())
				.setCap(cliente.getCap())
				.setVia(cliente.getVia())
				.setProvincia(cliente.getProvincia())
				.setComune(cliente.getComune())
				.setCarrello(mapCarrello(cliente))
				//.setOrdini(mapOrdini(cliente))
				.setRecensioni(mapRecensioni(cliente))
				.setUtente(mapUtente(cliente))
				.setWishlist(mapWishlist(cliente))
				.setDataRegistrazione(cliente.getDataRegistrazione())
				.build();
	}
	public static ClienteDTO mapClienteEssential(Cliente cliente) {
	
		return new ClienteDTO.Builder()
				.setIdCliente(cliente.getIdCliente()).setNome(cliente.getNome())
				.setCognome(cliente.getCognome()).setTelefono(cliente.getTelefono())
				.setImmagineCliente(cliente.getImmagineCliente())
				.setDataRegistrazione(cliente.getDataRegistrazione())
				.build();
	}
	// TODO: serve builder
		public static UtenteDTO mapUtente(Cliente cliente) {
			if (cliente.getUtente() == null) {
				return null;
			}
			Utente utente = cliente.getUtente();
			return new UtenteDTO.Builder()
					.setIdUtente(utente.getIdUtente())
					.setUsername(utente.getUsername())
					.setEmail(utente.getEmail())
					.setRoles(utente.getRoles().toString())
					// password non la mostro,e neanche cliente
					.build();
		}

	public static List<OrdineDTO> mapOrdini(Cliente cliente) {
		if (cliente.getOrdini() == null) {
			return Collections.emptyList();
		}

		return cliente.getOrdini().stream()
				.map(ordine -> new OrdineDTO.Builder()
						.dataOrdine(ordine.getDataOrdine())
						.idOrdine(ordine.getIdOrdine())
						.spedito(ordine.getSpedito())
						.totaleImporto(ordine.getTotaleImporto())
						.prodotti(null)
						.cliente(mapClienteToDTO(cliente))
						
						.build()).toList(); // da sistemare
	}

	public static List<ProdottoDTO> mapProdotti(Ordine ordine) {
		if (ordine.getProdotti() == null) {
			return Collections.emptyList();
		}

		return ordine.getProdotti().stream()
				.map(prodotto -> new ProdottoDTO.Builder()
						.idProdotto(prodotto.getId())
						.formato(prodotto.getProdotto().getFormato().toString())
						.titolo(prodotto.getProdotto().getTitolo())
						.artista(prodotto.getProdotto().getArtista())
						.genere(prodotto.getProdotto().getGenere())
						.prezzo(prodotto.getProdotto().getPrezzo())
						.quantita(prodotto.getQuantita())
						.descrizione(prodotto.getProdotto().getDescrizione())
						.annoPubblicazione(prodotto.getProdotto().getAnnoPubblicazione())
						.immagineProdotto(prodotto.getProdotto().getImmagineProdotto())
						
						
				.build())
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
						.setDataCreazione(feed.getDataCreazione())

						 .setCliente(mapClienteEssential(cliente)) 
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

		return new CarrelloDTO.Builder()
				.idCarrello(carrello.getIdCarrello())
				.cliente(null)
				.prodotti(null)
				//da aggiungere calcolo totale
				
				.build();
		
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