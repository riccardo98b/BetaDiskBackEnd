package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.OrdineRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.services.interfaces.OrdineService;

@RestController
@RequestMapping("/rest/ordine")
public class OrdineController {

	@Autowired
	Logger log;
	
	@Autowired
	OrdineService ordineServ;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) OrdineRequest request) {
		log.debug("Aggiungi prodotto: " + request);
		ResponseBase response = new ResponseBase();
		response.setRc(true);
		try {
			ordineServ.create(request);
			response.setMsg("Ordine inviato con successo");
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
}
