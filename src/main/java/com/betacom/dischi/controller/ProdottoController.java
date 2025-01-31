package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.services.interfaces.ProdottoService;

@RestController
@RequestMapping("/rest/prodotto")
public class ProdottoController {

	@Autowired
	Logger log;
	
	@Autowired
	ProdottoService prodottoService;
	
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true)ProdottoRequest req) {
		log.debug("Create prodotto: " + req);
		ResponseBase response = new ResponseBase();
		response.setrC(true);
		response.setMsg("Prdotto creato con successo!");
		try {
			prodottoService.create(req);
		}catch(Exception e) {
			response.setMsg(e.getMessage());
			response.setrC(false);
		}
		return response;
		
	}
}
