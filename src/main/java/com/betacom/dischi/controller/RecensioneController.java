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

import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.request.RecensioneRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.response.ResponseObject;
import com.betacom.dischi.services.interfaces.ClienteService;
import com.betacom.dischi.services.interfaces.RecensioneService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/recensione")
public class RecensioneController {
	@Autowired
	Logger log;
	@Autowired
	ClienteService clienteService;
	@Autowired
	RecensioneService recensioneService;
	
	
	@GetMapping("/list")
	public ResponseList<RecensioneDTO>list(Integer idRecensione,Integer stelle) {
		log.debug("Lista di tutte le recensioni: ");
		ResponseList<RecensioneDTO> response = new ResponseList<RecensioneDTO>();
		response.setRc(true);
		try {
			response.setDati(recensioneService.listAll(idRecensione,stelle)); 
			response.setRc(true);
	        response.setMsg("Visualizzazione lista recensioni");
		}catch(Exception e) {
			log.error(e.getMessage());
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
		
	}
	
	@GetMapping("/listById")
	public ResponseObject<RecensioneDTO> listById(@RequestParam Integer id){
		log.debug("Dati su recensione con id: " + id );
		ResponseObject<RecensioneDTO> response = new ResponseObject<RecensioneDTO>();
		try {
			response.setDati(recensioneService.listById(id));
			response.setRc(true);
	        response.setMsg("Visualizzazione dati recensioni con id: "+id);

		}catch(Exception e) {
			log.error(e.getMessage());
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
	}
	
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) RecensioneRequest req) {
		ResponseBase response = new ResponseBase();
		log.debug(req.toString());
		try {
			recensioneService.create(req);
			response.setRc(true);
	        response.setMsg("Recensione creata con successo!");
		}
		catch(Exception e) {
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) RecensioneRequest req) {
		ResponseBase response = new ResponseBase();
		log.debug(req.toString());
		try {
			recensioneService.update(req);
			response.setRc(true);
	        response.setMsg("Recensione aggiornata con successo!");
		}
		catch(Exception e) {
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) RecensioneRequest req) {
	    ResponseBase response = new ResponseBase();
	    try {
	        recensioneService.delete(req);
	        response.setRc(true); // 
	        response.setMsg("Recensione eliminata con successo!");
	    } catch (Exception e) {
	        response.setRc(false); 
	        response.setMsg(e.getMessage());
	    }
	    return response;
	}
	
	@GetMapping("/lista-recensioni")
	public ResponseList<RecensioneDTO>listaRecensioni(Integer id) {
		log.debug("Lista di tutte le recensioni: " + id);
		ResponseList<RecensioneDTO> response = new ResponseList<RecensioneDTO>();
		response.setRc(true);
		try {
			response.setDati(recensioneService.listaProdottiDaRecensire(id)); 
			response.setRc(true);
	        response.setMsg("Visualizzazione lista recensioni");
		}catch(Exception e) {
			log.error(e.getMessage());
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
		
	}

}
