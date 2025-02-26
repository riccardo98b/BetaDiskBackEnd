package com.betacom.dischi;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.controller.ProdottoController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.utilities.enums.Formato;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoControllerTest {

	@Autowired
	ProdottoController prodottoController;
		
	@Autowired
	Logger log;
	
	
	
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
	public void createProdottoTest(){
		ProdottoRequest req = createProdottoGeneralRequest();
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
		
	}
	
	@Test
	@Order(2)
	public void listAllProdotti() {
		
		String titolo = "Among the Living";
		

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, titolo, null, null, null);
	
	    List<ProdottoDTO> dati = lista.getDati();
	    
		Assertions.assertThat(dati).isNotEmpty();
		
}

	@Test
	@Order(3)
	public void listAllProdottiError() throws CustomException{

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(50, null, null, null, null);
		
		Assertions.assertThat(lista.getRc()).isEqualTo(false);
		
}
	
	@Test
	@Order(4)
	public void listaFiltrataFormato() {
		Formato formato = Formato.VINILE;
		
		ResponseList<ProdottoDTO> lista = prodottoController.listFormato(formato);

		Assertions.assertThat(lista).isNotNull();
		
		Assertions.assertThat(lista
				.getDati()
				.stream()
				.anyMatch(t -> t.getFormato()
						.equals(formato.toString()))).isTrue();	
		
	}
	
	
	
	@Test
	@Order(5)
	public void updateProdotto() {
		
		ProdottoRequest req = createProdottoGeneralRequest();
		
		req.setTitolo("Ops ho cambiato il titolo");
		
		ResponseBase response = prodottoController.update(req);
		
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		Assertions.assertThat(req.getTitolo()).isEqualTo("Ops ho cambiato il titolo");
									
	}
	
	
	
	@Test
	@Order(6)
	public void deleteProdotto() {
	
		ProdottoRequest req = createProdottoGeneralRequest();
		ResponseBase response = prodottoController.delete(req);
		
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(7)
	public void createProdottoTestPerSuccessivo(){
		ProdottoRequest req = createProdottoGeneralRequest();
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
	}
	@Test
	@Order(8)
	public void createProdottoTestPerSuccessivo2(){
		ProdottoRequest req = createProdottoGeneralRequest();
		req.setTitolo("Among the Living2");
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
	}
	
}
