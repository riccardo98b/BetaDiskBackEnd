package com.betacom.dischi.services.interfaces;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.request.CarrelloRequest;

public interface CarrelloService {

	public Carrello create(CarrelloRequest request) throws CustomException;
	public void addProdotto(CarrelloRequest request) throws CustomException;
	public void removeProdotto(CarrelloRequest request) throws CustomException;
	public void delete(CarrelloRequest request) throws CustomException;
	CarrelloDTO listaProdotti(Integer idCliente) throws CustomException;
	
}
