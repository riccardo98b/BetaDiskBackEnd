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

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.response.ResponseObject;
import com.betacom.dischi.services.interfaces.UtenteService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/utente")
public class UtenteController {
	@Autowired
	Logger log;

	
	@Autowired
	UtenteService utenteService;
	
	
	@GetMapping("/listAll")
	public ResponseList<UtenteDTO>list(Integer idUtente,String username,String email) {
		log.debug("Lista di tutti gli utenti: ");
		ResponseList<UtenteDTO> response = new ResponseList<UtenteDTO>();
		response.setRc(true);
		try {
			response.setDati(utenteService.listAll(idUtente,username,email)); 
			response.setRc(true);
	        response.setMsg("Visualizzazione lista utenti");
		}catch(Exception e) {
			log.error(e.getMessage());
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
		
	}
	
	
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) UtenteRequest req) {
		ResponseBase response = new ResponseBase();
		log.debug(req.toString());
		try {
			utenteService.createUser(req);
			response.setRc(true);
	        response.setMsg("Utente creato con successo!");
		}
		catch(Exception e) {
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
	}
	

	
	@PostMapping("/signin")
	public SignInDTO signIn(@RequestBody(required = true) SignInRequest req) {
		return utenteService.signIn(req);
	}
	
	@GetMapping("/listById")
	public ResponseObject<UtenteDTO> listById(@RequestParam Integer id){
		log.debug("List " + id );
		ResponseObject<UtenteDTO> response = new ResponseObject<UtenteDTO>();
		try {
			response.setDati(utenteService.listById(id));
			response.setRc(true);
	        response.setMsg("Visualizzazione dati cliente con id: "+id);

		}catch(Exception e) {
			log.error(e.getMessage());
			response.setMsg(e.getMessage());
			response.setRc(false);
		}
		return response;
	}
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) UtenteRequest req) {
	    ResponseBase response = new ResponseBase();
	    try {
	        utenteService.deleteUtente(req.getIdUtente());
	        response.setRc(true); // 
	        response.setMsg("Utente eliminato con successo!");
	    } catch (Exception e) {
	        response.setRc(false); 
	        response.setMsg(e.getMessage());
	    }
	    return response;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) UtenteRequest req) {
	    ResponseBase response = new ResponseBase();
	    try {
	        utenteService.updateUtente(req);
	        response.setRc(true); // 
	        response.setMsg("Utente aggiornato con successo!");
	    } catch (Exception e) {
	        response.setRc(false); 
	        response.setMsg(e.getMessage());
	    }
	    return response;
	}
	
	
}
