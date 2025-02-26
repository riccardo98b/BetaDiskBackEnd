package com.betacom.dischi;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.OrdineDTO;
import com.betacom.dischi.controller.CarrelloController;
import com.betacom.dischi.controller.OrdineController;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.OrdineRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineControllerTest {

	@Autowired
	OrdineController controller;
	
	@Autowired
	CarrelloController controllerP;
	
	@Test
	@Order(0)
	public void listaOrdiniAdminErr() {
		ResponseList<OrdineDTO> response = controller.listAll(LocalDate.now().toString());
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(1)
	public void creaOrdine(){
		OrdineRequest request = new OrdineRequest();
		request.setIdCliente(2);
		ResponseBase response = controller.create(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	@Test
	@Order(10)
	public void creaOrdineErr(){
		OrdineRequest request = new OrdineRequest();
		request.setIdCliente(2);
		ResponseBase response = controller.create(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(2)
	public void updateOrdine(){
		OrdineRequest request = new OrdineRequest();
		request.setIdOrdine(1);
		request.setSpedito(true);
		ResponseBase response = controller.update(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(3)
	public void updateOrdineErr(){
		OrdineRequest request = new OrdineRequest();
		request.setIdOrdine(1);
		request.setSpedito(false);
		ResponseBase response = controller.update(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	@Test
	@Order(11)
	public void deleteOrdineErr(){
		OrdineRequest request = new OrdineRequest();
		request.setIdOrdine(1);
		ResponseBase response = controller.delete(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(4)
	public void listaOrdini() {
		ResponseList<OrdineDTO> response = controller.listaOrdini(2);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		response = controller.listaOrdini(1);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(5)
	public void addProdottoPerSuccessivo(){
		CarrelloRequest request = new CarrelloRequest();
		request.setIdCliente(2);
		request.setIdProdotto(2);
		request.setQuantita(1);
		ResponseBase response = controllerP.addProdotto(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	@Test
	@Order(6)
	public void creaOrdineSuccessivo(){
		OrdineRequest request = new OrdineRequest();
		request.setIdCliente(2);
		ResponseBase response = controller.create(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(7)
	public void updateOrdinePost(){
		OrdineRequest request = new OrdineRequest();
		request.setIdOrdine(2);
		request.setSpedito(false);
		ResponseBase response = controller.update(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(8)
	public void deleteOrdine(){
		OrdineRequest request = new OrdineRequest();
		request.setIdOrdine(2);
		ResponseBase response = controller.delete(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(9)
	public void listaOrdiniAdmin() {
		ResponseList<OrdineDTO> response = controller.listAll(LocalDate.now().toString());
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		Assertions.assertThat(response.getDati().size()).isGreaterThan(0);
	}
	@Test
	@Order(9)
	public void listaOrdiniAdmin2() {
		ResponseList<OrdineDTO> response = controller.listAll("");
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		Assertions.assertThat(response.getDati().size()).isGreaterThan(0);
	}
}
