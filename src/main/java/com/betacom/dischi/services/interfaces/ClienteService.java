package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.request.ClienteRequest;

public interface ClienteService {

	List<ClienteDTO> listAll();	
	

	
	void create(ClienteRequest req) throws Exception;
		
	 void update(ClienteRequest req) throws Exception;
	    
	    
    
	void delete(ClienteRequest req) throws Exception;
}
