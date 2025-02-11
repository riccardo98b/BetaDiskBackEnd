package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.controller.OrdineController;
import com.betacom.dischi.request.OrdineRequest;
import com.betacom.dischi.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineControllerTest {

	@Autowired
	OrdineController controller;
	
	@Test
	@Order(1)
	public void creaOrdine(){
		OrdineRequest request = new OrdineRequest();
		request.setIdCliente(1);
		ResponseBase response = controller.create(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	
}
