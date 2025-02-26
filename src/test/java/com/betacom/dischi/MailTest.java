package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.controller.MailController;
import com.betacom.dischi.request.MailRequest;
import com.betacom.dischi.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailTest {

	@Autowired
	MailController controller;
	
	@Test
	@Order(1)
	public void confermaOrdine() {
		MailRequest request = new MailRequest();
		ResponseBase response = controller.getConfermaOrdine(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
		
	}
	
	@Test
	@Order(2)
	public void ordineSpedito() {
		MailRequest request = new MailRequest();
		ResponseBase response = controller.ordineSpedito(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(3)
	public void confermaRegistrazione() {
		MailRequest request = new MailRequest();
		ResponseBase response = controller.getConfermaRegistrazione(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(4)
	public void confermaRegistrazioneAdmin() {
		MailRequest request = new MailRequest();
		ResponseBase response = controller.getConfermaRegistrazioneAdminNonCliente(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
 }
