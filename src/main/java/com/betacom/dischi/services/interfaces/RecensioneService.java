package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.request.RecensioneRequest;

public interface RecensioneService {

	List<RecensioneDTO> listAll();

	void create(RecensioneRequest req) throws Exception;

	void update(RecensioneRequest req) throws Exception;

	void delete(RecensioneRequest req) throws Exception;

	RecensioneDTO listById(Integer id);

}
