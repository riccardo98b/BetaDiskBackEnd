package com.betacom.dischi.utilities;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.OrdineDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Ordine;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Recensione;
import com.betacom.dischi.request.ProdottoRequest;

public class Utility {

	public static void validazioneValoriProdotto(ProdottoRequest req) throws CustomException{
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
	}
	
	public static ProdottoDTO buildProdottoDTO(Prodotto prodotto) {
		return new ProdottoDTO.Builder()
				.idProdotto(prodotto.getIdProdotto())
				.formato(prodotto.getFormato().toString())
				.titolo(prodotto.getTitolo())
				.artista(prodotto.getArtista())
				.genere(prodotto.getGenere())
				.descrizione(prodotto.getDescrizione())
				.annoPubblicazione(prodotto.getAnnoPubblicazione())
				.prezzo(prodotto.getPrezzo())
				.quantita(prodotto.getQuantita())
				.immagineProdotto(prodotto.getImmagineProdotto())
				.recensioni(prodotto.getRecensioni().stream().map(r -> buildRecensioneDTO(r)).toList())
				.build();
	}
	 public static RecensioneDTO buildRecensioneDTO(Recensione recensione) {
		 return new RecensioneDTO.Builder()
				 .idRecensione(recensione.getIdRecensione())
				 .descrizione(recensione.getDescrizione())
				 .stelle(recensione.getStelle())
				 .dataCreazione(recensione.getDataCreazione())
				 .cliente(null)
				 .build();
	 }
	 
	 public static ClienteDTO buildClienteDTO(Cliente cliente) {
		 return new ClienteDTO.Builder()
				 .idCliente(cliente.getIdCliente())
				 .nome(cliente.getNome())
				 .cognome(cliente.getCognome())
				 .telefono(cliente.getTelefono())
				 .immagineCliente(cliente.getImmagineCliente())
				 .via(cliente.getVia())
				 .cap(cliente.getCap())
				 .provincia(cliente.getProvincia())
				 .comune(cliente.getComune())
				 .dataRegistrazione(cliente.getDataRegistrazione())
				 .ordini(cliente.getOrdini().stream().map(o -> buildOrdineDTO(o)).toList())
				 .carrello(buildCarrelloDTO(cliente.getCarrello()))
				 .utente(null)
				 .wishlist(null)
				 .recensioni(cliente.getRecensioni().stream().map(r -> buildRecensioneDTO(r)).toList())
				 .build();
	 }

	 public static OrdineDTO buildOrdineDTO(Ordine ordine) {
		 return new OrdineDTO.Builder()
				 .build();
	 }
	 
	 public static CarrelloDTO buildCarrelloDTO(Carrello carrello) {
		 return new CarrelloDTO.Builder()
				 .build();
	 }
}
