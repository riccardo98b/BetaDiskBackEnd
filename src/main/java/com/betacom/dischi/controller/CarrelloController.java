package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.services.interfaces.CarrelloService;

@RestController
@RequestMapping("/rest/carrello")
public class CarrelloController {

	@Autowired
	Logger log;
	
	@Autowired
	CarrelloService carrelloServ;
	
	@PostMapping("/add")
	public ResponseBase addProdotto(@RequestBody(required = true) CarrelloRequest request, @RequestBody ProdottoRequest prodRequest) {
		log.debug("Aggiungi prodotto: " + prodRequest);
		ResponseBase response = new ResponseBase();
		response.setRc(true);
		try {
			carrelloServ.addProdotto(request, prodRequest);
			response.setMsg("Prodotto aggiunto con successo");
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
}
