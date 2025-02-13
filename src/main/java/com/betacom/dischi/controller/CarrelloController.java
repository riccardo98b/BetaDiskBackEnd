package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseObject;
import com.betacom.dischi.services.interfaces.CarrelloService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/carrello")
public class CarrelloController {

	@Autowired
	Logger log;
	
	@Autowired
	CarrelloService carrelloServ;
	
	@PostMapping("/add")
	public ResponseBase addProdotto(@RequestBody(required = true) CarrelloRequest request) {
		log.debug("Aggiungi prodotto: " + request);
		ResponseBase response = new ResponseBase();
		response.setRc(true);
		try {
			carrelloServ.addProdotto(request);
			response.setMsg("Prodotto aggiunto con successo");
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/remove")
	public ResponseBase removeProdotto(@RequestBody(required = true) CarrelloRequest request) {
		log.debug("Rimuovi prodotto: " + request);
		ResponseBase response = new ResponseBase();
		response.setRc(true);
		try {
			carrelloServ.removeProdotto(request);
			response.setMsg("Prodotto rimosso con successo");
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/delete")
	public ResponseBase deleteCarrello(@RequestBody(required = true) CarrelloRequest request) {
		log.debug("cancella carrello: " + request);
		ResponseBase response = new ResponseBase();
		response.setRc(true);
		try {
			carrelloServ.delete(request);
			response.setMsg("Carrello eliminato con successo");
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	@GetMapping("/lista")
	public ResponseObject<CarrelloDTO> listaProdotti(@RequestParam Integer id) {
		log.debug("Lista carrello: " + id);
		ResponseObject<CarrelloDTO> response = new ResponseObject<CarrelloDTO>();
		response.setRc(true);
		try {	
			response.setMsg("Prodotti nel carrello");
			response.setDati(carrelloServ.listaProdotti(id));
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
}
