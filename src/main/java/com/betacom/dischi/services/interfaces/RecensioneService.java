package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.RecensioneRequest;

public interface RecensioneService {

	List<RecensioneDTO> listAll(Integer idRecensione,Integer stelle);

	void create(RecensioneRequest req) throws CustomException;

	void update(RecensioneRequest req) throws CustomException;

	void delete(RecensioneRequest req) throws CustomException;

	RecensioneDTO listById(Integer id) throws CustomException;

	List<RecensioneDTO> listReviewsByProduct(Integer idProdotto) throws CustomException;

}
