package com.betacom.dischi.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.services.interfaces.ClienteService;

@Service
public class ClienteImpl implements ClienteService {

	@Override
	public List<ClienteDTO> listAll() {
		return null;
	}

	@Override
	public void create(ClienteRequest req) throws Exception {
		
	}

	@Override
	public void update(ClienteRequest req) throws Exception {
		
	}

	@Override
	public void delete(ClienteRequest req) throws Exception {
		
	}

}
