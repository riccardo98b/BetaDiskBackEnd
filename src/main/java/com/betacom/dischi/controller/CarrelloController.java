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
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.services.interfaces.CarrelloService;

@RestController
@RequestMapping("/rest/carrello")
public class CarrelloController {

	@Autowired
	Logger log;
	
	@Autowired
	CarrelloService carrelloServ;
	
	@PostMapping("/add")
	public ResponseList<CarrelloDTO> addProdotto(@RequestBody(required = true) CarrelloRequest request, @RequestBody ProdottoRequest prodRequest) {
		log.debug("Aggiungi prodotto: " + prodRequest);
		ResponseList<CarrelloDTO> response = new ResponseList<CarrelloDTO>();
		response.setRc(true);
		try {
			carrelloServ.addProdotto(request, prodRequest);
			response.setMsg("Prodotto aggiunto con successo");
			response.setDati(carrelloServ.listaProdotti(request.getIdCliente()));
		} catch (CustomException e) {
			log.error(e.getMessage());
			response.setRc(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
}
