package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.RecensioneDTO;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.ProdottoController;
import com.betacom.dischi.controller.RecensioneController;
import com.betacom.dischi.controller.UtenteController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.RecensioneRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecensioneControllerTest {
	
	@Autowired
	ProdottoController prodottoController;
	@Autowired
	ClienteController clienteController;
	@Autowired
	UtenteController utenteController;
	@Autowired
	RecensioneController recensioneController;
	
	private RecensioneRequest reqRecensione;
	final Integer VALID_ID = 2;
	final Integer INVALID_ID = -2;
	
	
	//TODO: SERVE ORDINE PER COMPLETARE I TEST
	// cliente
	@Test
	@Order(1)
	public void createRecensione_withInvalidValidData_shouldNotCreateRecensione()  {
		reqRecensione = new RecensioneRequest();
		reqRecensione.setIdCliente(INVALID_ID);
		reqRecensione.setIdProdotto(VALID_ID);
		reqRecensione.setDescrizione("");
		reqRecensione.setStelle(0);
		ResponseBase responseRecensione = recensioneController.create(reqRecensione);
		Assertions.assertThatExceptionOfType(CustomException.class)
        .isThrownBy(() -> recensioneController.create(reqRecensione));
	}
	
	@Test
	@Order(2)
	public void createRecensione_withValidData_shouldCreateRecensione()  {
		reqRecensione = new RecensioneRequest();
		reqRecensione.setIdCliente(VALID_ID);
		reqRecensione.setIdProdotto(VALID_ID);
		reqRecensione.setDescrizione("Prodotto di alta qualità");
		reqRecensione.setStelle(5);
		ResponseBase responseRecensione = recensioneController.create(reqRecensione);
		  Assertions.assertThatCode(() -> recensioneController.create(reqRecensione))
          .doesNotThrowAnyException();	
	}

	@Test
	@Order(3)
	public void listAllRecensione_shouldReturnListOfRecensione() {
		ResponseList<RecensioneDTO> responseList = recensioneController.list(null, null);
	    Assertions.assertThat(responseList).isNotNull();
	}
	
	
	@Test
	@Order(4)
	public void listById_withValidId_shouldReturnRecensione()  {
		ResponseBase response = recensioneController.listById(1);
		  Assertions.assertThatCode(() -> recensioneController.listById(1))
          .doesNotThrowAnyException();
		  }
	

	@Test
	@Order(5)
	public void listById_withInvalidId_shouldNotReturnRecensione()  {
		ResponseBase response = recensioneController.listById(INVALID_ID);
		Assertions.assertThatExceptionOfType(CustomException.class)
        .isThrownBy(() -> recensioneController.listById(INVALID_ID));
		  }
	
	
	@Test
	@Order(6)
	public void deleteRecensione_withValidId_shouldDeleteRecensione() {
	    reqRecensione = new RecensioneRequest();
	    reqRecensione.setIdRecensione(1);     
	    ResponseBase response = recensioneController.delete(reqRecensione);
	    Assertions.assertThat(response).isNotNull();
		Assertions.assertThatExceptionOfType(CustomException.class)
		.isThrownBy(() -> recensioneController.listById(1));
	}

	@Test
	@Order(7)
	public void deleteRecensione_withInvalidId_shouldNotDeleteRecensione() {
	    reqRecensione = new RecensioneRequest();
	    reqRecensione.setIdRecensione(INVALID_ID); 
	    ResponseBase response = recensioneController.delete(reqRecensione);
	    Assertions.assertThat(response.getRc()).isEqualTo(false); 
	}
	@Test
	@Order(8)
	public void createRecensione_withInvalidData_shouldThrowException()  {
		RecensioneRequest reqInvalid = new RecensioneRequest();
		reqInvalid.setIdCliente(VALID_ID);
		reqInvalid.setIdProdotto(INVALID_ID);
		reqInvalid.setDescrizione("Prodotto di alta qualità,consigliato");
		reqInvalid.setStelle(0);
		ResponseBase responseRecensione = recensioneController.create(reqInvalid);
		Assertions.assertThatExceptionOfType(CustomException.class)
		.isThrownBy(() -> recensioneController.create(reqInvalid));
	}
	
	@Test
	@Order(8)
	public void listaRecensioniDaAcquisti() {
		ResponseList<RecensioneDTO> responseList = recensioneController.listaRecensioni(1);
		Assertions.assertThat(responseList.getDati().size()).isGreaterThan(0);
	}
	
	

}
