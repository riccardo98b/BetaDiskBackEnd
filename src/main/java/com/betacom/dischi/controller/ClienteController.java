package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.services.interfaces.ClienteService;


	
	@RestController
	@RequestMapping("/rest/cliente")
	public class ClienteController {
		@Autowired
		Logger log;
		@Autowired
		ClienteService clienteService;
		
		@GetMapping("/listAll")
		public ResponseList<ClienteDTO>list() {
			log.debug("Lista di tutti i clienti: ");
			ResponseList<ClienteDTO> response = new ResponseList<ClienteDTO>();
			response.setrC(true);
			try {
				response.setDati(clienteService.listAll()); 
			}catch(Exception e) {
				log.error(e.getMessage());
				response.setMsg(e.getMessage());
				response.setrC(false);
			}
			return response;
			
		}
		
		@PostMapping("/create")
		public ResponseBase create(@RequestBody(required = true) ClienteRequest req) {
			ResponseBase response = new ResponseBase();
			response.setrC(true);
			log.debug(req.toString());
			try {
				clienteService.create(req);
			}
			catch(Exception e) {
				response.setMsg(e.getMessage());
				response.setrC(false);
			}
			return response;
		}
		
		@PostMapping("/update")
		public ResponseBase update(@RequestBody(required = true) ClienteRequest req) {
			ResponseBase response = new ResponseBase();
			response.setrC(true);
			log.debug(req.toString());
			try {
				clienteService.update(req);
			}
			catch(Exception e) {
				response.setMsg(e.getMessage());
				response.setrC(false);
			}		
			return response;
		}
		
		@PostMapping("/delete")
		public ResponseBase delete(@RequestBody(required = true) ClienteRequest req) {
		    ResponseBase response = new ResponseBase();
		    try {
		        clienteService.delete(req);
		        response.setrC(true); // 
		        response.setMsg("Cliente eliminato con successo!");
		    } catch (Exception e) {
		        response.setrC(false); 
		        response.setMsg(e.getMessage());
		    }
		    
		    if (response.getrC() == null) {
		        response.setrC(false);  
		    }
		    return response;
		}
		


}
