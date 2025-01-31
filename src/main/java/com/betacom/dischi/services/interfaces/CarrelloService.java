package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Carrello;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.ProdottoRequest;

public interface CarrelloService {

	public Carrello create(CarrelloRequest request) throws CustomException;
	public void addProdotto(CarrelloRequest request, ProdottoRequest prodRequest) throws CustomException;
	public void removeProdotto(CarrelloRequest request, ProdottoRequest prodRequest) throws CustomException;
	public void delete(CarrelloRequest request) throws CustomException;
	List<CarrelloDTO> listaProdotti(Integer idCliente) throws CustomException;
	
}
