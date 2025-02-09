package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.request.ClienteRequest;

public interface ClienteService {

	
	ClienteDTO listById(Integer id) throws Exception;

	void create(ClienteRequest req) throws Exception;

	void update(ClienteRequest req) throws Exception;

	void delete(ClienteRequest req) throws Exception;

	List<ClienteDTO> listAll(Integer idCliente, String nome, String cognome);

}
