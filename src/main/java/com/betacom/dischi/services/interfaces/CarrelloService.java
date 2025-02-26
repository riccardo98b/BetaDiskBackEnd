package com.betacom.dischi.services.interfaces;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.request.CarrelloRequest;

public interface CarrelloService {

//	Carrello create(CarrelloRequest request) throws CustomException;
	void addProdotto(CarrelloRequest request) throws CustomException;
	void removeProdotto(CarrelloRequest request) throws CustomException;
	void delete(CarrelloRequest request) throws CustomException;
	CarrelloDTO listaProdotti(Integer idCliente) throws CustomException;
	
}
