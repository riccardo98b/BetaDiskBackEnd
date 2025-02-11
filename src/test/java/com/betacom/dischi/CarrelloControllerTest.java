package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.CarrelloDTO;
import com.betacom.dischi.controller.CarrelloController;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.ProdottoController;
import com.betacom.dischi.request.CarrelloRequest;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloControllerTest {

	@Autowired
	CarrelloController carrelloController;
	
	@Autowired
	ProdottoController prodottoController;
	
	@Autowired
	ClienteController clienteController;
	
	public ProdottoRequest createProdottoGeneralRequest() {
		ProdottoRequest req = new ProdottoRequest();
		req.setIdProdotto(1);
		req.setFormato("VINILE");
		req.setTitolo("Among the Living");
		req.setArtista("Anthrax");
		req.setGenere("Thrash Metal");
		req.setDescrizione("Album iconico del thrash metal, pubblicato nel 1987.");
		req.setAnnoPubblicazione(1987);
		req.setPrezzo(24.99);
		req.setQuantita(8);
		req.setImmagineProdotto("https://example.com/among-the-living.jpg");
		return req;
	}
	
	@Test
	@Order(1)
	public void createProdottoTest()throws Exception {
		ProdottoRequest req = createProdottoGeneralRequest();
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void createCliente() {
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
	@Order(3)
	public void addProdotto(){
		CarrelloRequest request = new CarrelloRequest();
		request.setIdCliente(1);
		request.setIdProdotto(1);
		request.setQuantita(1);
		ResponseBase response = carrelloController.addProdotto(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
	public void addProdottoErrore(){
		CarrelloRequest request = new CarrelloRequest();
		request.setIdCliente(2);
		request.setIdProdotto(1);
		request.setQuantita(1);
		ResponseBase response = carrelloController.addProdotto(request);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
		Assertions.assertThat(response.getMsg()).isEqualTo("Cliente inesistente");
	}
	
	@Test
	@Order(5)
	public void listaProdotto() {
		System.out.println("inizio");
		addProdotto();
		ResponseObject<CarrelloDTO> response = carrelloController.listaProdotti(1);
		System.out.println(response.getMsg()+ "!!");
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		response = carrelloController.listaProdotti(2);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(6)
	public void removeProdotto(){
		CarrelloRequest request = new CarrelloRequest();
		request.setIdCliente(1);
		request.setIdProdotto(1);
		request.setQuantita(1);
		ResponseBase response = carrelloController.removeProdotto(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(7)
	public void svuotaCarrello(){
		CarrelloRequest request = new CarrelloRequest();
		request.setIdCliente(1);
		ResponseBase response = carrelloController.deleteCarrello(request);
		Assertions.assertThat(response.getRc()).isEqualTo(true);
	}
 }
