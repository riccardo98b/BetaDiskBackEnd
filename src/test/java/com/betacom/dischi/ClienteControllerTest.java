package com.betacom.dischi;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.response.ResponseBase;
import org.assertj.core.api.Assertions;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteControllerTest {
	
	@Autowired
	ClienteController clienteController;
	
	@Autowired
	Logger log;
	
	
	@Test
	@Order(1)
	public void createClienteTest() throws CustomException{
		ClienteRequest req = new ClienteRequest();
		req.setNome("Matteo");
		req.setCognome("Bianchi");
		req.setImmagineCliente("https://randomuser.me/api/portraits/women/9.jpg");
		req.setTelefono("3456401123");	
		ResponseBase response = clienteController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}

}
