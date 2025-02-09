package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.UtenteController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {

	@Autowired
	ClienteController clienteController;

	@Autowired
	UtenteController utenteController;

	// convenzione nomi metodi test: nomeMetodo_condizione_risultatoAspettato

	private ClienteRequest reqCliente;
	private UtenteRequest reqUtente;

	final Integer VALID_ID = 1;
	final Integer INVALID_ID = -2;


	@BeforeEach
	public void setup() {
		reqCliente = new ClienteRequest();
		// cliente
		reqCliente.setNome("Matteo");
		reqCliente.setCognome("Bianchi");
		reqCliente.setImmagineCliente("https://randomuser.me/api/portraits/men/9.jpg");
		reqCliente.setTelefono("3456401123");
		//
        reqUtente = new UtenteRequest();

	}

	@Test
	@Order(1)
	public void createUtente_withValidData_shouldCreateUtente() throws CustomException {
		ResponseBase responseCliente = clienteController.create(reqCliente);
		reqUtente.setEmail("matteob@gmail.com");
		reqUtente.setIdCliente(1);
		reqUtente.setPassword("password");
		reqUtente.setRoles("UTENTE");
		reqUtente.setUsername("matteob");
		ResponseBase responseUtente = utenteController.create(reqUtente);
		Assertions.assertThat(responseUtente.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void listAllUtente_shouldReturnListOfUtente(Integer idUtente,String username,String email) throws CustomException{
		ResponseList<UtenteDTO> responseList = utenteController.list(null, null, null);
	    Assertions.assertThat(responseList).isNotNull();
	    Assertions.assertThat(responseList.getDati()).isNotEmpty();
	}
	
	

//	@Test
//	@Order(2)
//	public void updateUtente_withValidData_shouldCreateUtente() {
//		reqUtente.setIdUtente(VALID_ID);
//		reqUtente.setEmail("matteob@gmail.com");
//		reqUtente.setIdCliente(1);
//		reqUtente.setPassword("password");
//		reqUtente.setRoles("UTENTE");
//		reqUtente.setUsername("matteobianco");
//		// utenteController.update(reqUtente);
//
//		ResponseObject<UtenteDTO> updatedUtente = utenteController.listById(VALID_ID);
//		Assertions.assertThat(updatedUtente.getDati().getUsername()).isEqualTo("matteobianco");
//	}
	@Test
	@Order(3)
	public void listById_withValidId_shouldReturnUtente() {
		ResponseBase response = utenteController.listById(VALID_ID);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	@Test
	@Order(4)
	public void listById_withInvalidId_shouldThrowException() {
		Assertions.assertThatExceptionOfType(CustomException.class)
		.isThrownBy(() -> utenteController.listById(INVALID_ID));
	}
	@Test
	@Order(5)
	public void deleteUtente_withInvalidId_shouldNotDeleteUtente() {
		reqUtente.setIdUtente(INVALID_ID);
		ResponseBase response = utenteController.delete(reqUtente);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
		Assertions.assertThatExceptionOfType(CustomException.class)
        .isThrownBy(() -> utenteController.listById(INVALID_ID));
	}
	
	@Test
	@Order(6)
	public void deleteUtente_withValidId_shouldDeleteUtente() {
		reqUtente.setIdUtente(VALID_ID);
		ResponseBase response = utenteController.delete(reqUtente);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	
	}


}
