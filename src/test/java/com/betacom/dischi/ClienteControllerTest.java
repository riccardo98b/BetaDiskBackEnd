package com.betacom.dischi;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.response.ResponseObject;


import org.assertj.core.api.Assertions;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteControllerTest {
	
	@Autowired
	ClienteController clienteController;

	final Integer VALID_ID = 1;
	final Integer INVALID_ID = -2;
	// convenzione nomi metodi test: nomeMetodo_condizione_risultatoAspettato
	@Test
	@Order(1)
	public void createCliente_withValidData_shouldCreateCliente() throws CustomException {
		ClienteRequest req = new ClienteRequest();
		req.setNome("Matteo");
		req.setCognome("Bianchi");
		req.setImmagineCliente("https://randomuser.me/api/portraits/women/9.jpg");
		req.setTelefono("3456401123");	
		req.setCap("35028");
		req.setComune("Polverara");
		req.setProvincia("PD");
		req.setVia("via Roma,10");
		ResponseBase response = clienteController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void createCliente_withoutImage_shouldCreateCliente() throws CustomException {
		ClienteRequest req = new ClienteRequest();
		req.setNome("Carla");
		req.setCognome("Rossi");
		req.setTelefono("3456401129");	
		req.setCap("35028");
		req.setComune("Polverara");
		req.setProvincia("PD");
		req.setVia("via Roma,10");
		ResponseBase response = clienteController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(3)
	public void createCliente_withInvalidPhoneNumber_shouldThrowException() throws CustomException {
		ClienteRequest req = new ClienteRequest();
		req.setNome("Maria");
		req.setCognome("Bianca");
		req.setTelefono("dcnsdj23");	
		req.setImmagineCliente("https://randomuser.me/api/portraits/women/9.jpg");
		req.setCap("35028");
		req.setComune("Polverara");
		req.setProvincia("PD");
		req.setVia("via Roma,10");
		Assertions.assertThatExceptionOfType(CustomException.class)
        .isThrownBy(() -> clienteController.create(req));
	}
	
	@Test
	@Order(4)
	public void createCliente_withoutRequiredFields_shouldReturnErrorAndThrowException() throws CustomException {
		ClienteRequest req = new ClienteRequest();
		req.setImmagineCliente("https://randomuser.me/api/portraits/women/9.jpg");

		ResponseBase response = clienteController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
		Assertions.assertThatExceptionOfType(CustomException.class)
        .isThrownBy(() -> clienteController.create(req));
	}
	
	@Test
	@Order(5)
	public void updateCliente_withValidData_shouldUpdateCliente() throws CustomException {
	    ClienteRequest req = new ClienteRequest();
	    req.setIdCliente(VALID_ID);
	    req.setNome("Paolo");
	    req.setCognome("Cerco");
	    req.setImmagineCliente("https://google.com");
	    req.setTelefono("123456789");
		req.setCap("35028");
		req.setComune("Polverara");
		req.setProvincia("PD");
		req.setVia("via Roma,10");

	    ResponseBase response = clienteController.update(req);
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    
	    ResponseObject<ClienteDTO> updatedCliente = clienteController.listById(VALID_ID);
	    Assertions.assertThat(updatedCliente.getDati().getNome()).isEqualTo("Paolo");
	    Assertions.assertThat(updatedCliente.getDati().getCognome()).isEqualTo("Cerco");
	}
	
	@Test
	@Order(6)
	public void updateCliente_withInvalidId_shouldThrowException() {
		ClienteRequest req = new ClienteRequest();
		req.setIdCliente(INVALID_ID);
		req.setNome("Gianni");
		req.setCognome("Verde");
		req.setCap("35028");
		req.setComune("Polverara");
		req.setProvincia("PD");
		req.setVia("via Roma,10");
		Assertions.assertThatExceptionOfType(CustomException.class)
		    .isThrownBy(() -> clienteController.update(req));
	}

	@Test
	@Order(7)
	public void listAllClientes_shouldReturnListOfCliente(Integer idCliente,String nome,String cognome) throws CustomException {
	    ResponseList<ClienteDTO> responseList = clienteController.list(null,null,null);
	    Assertions.assertThat(responseList).isNotNull();
	    Assertions.assertThat(responseList.getDati()).isNotEmpty();
	}
	
	@Test
	@Order(8)
	public void listById_withInvalidId_shouldThrowException() {
		Assertions.assertThatExceptionOfType(CustomException.class)
		.isThrownBy(() -> clienteController.listById(INVALID_ID));
	}
	
	@Test
	@Order(9)
	public void deleteCliente_withValidId_shouldDeleteCliente() {
		ClienteRequest req = new ClienteRequest();
		req.setIdCliente(VALID_ID);
		ResponseBase response = clienteController.delete(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);

	}
}