package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.OrdineDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.OrdineRequest;

public interface OrdineService {

	void create(OrdineRequest request) throws CustomException;
	void delete(OrdineRequest request) throws CustomException;
	void update(OrdineRequest request) throws CustomException;
	List<OrdineDTO> listaByCliente(Integer id) throws CustomException;
	List<OrdineDTO> listAll(String request) throws CustomException;
	
}
