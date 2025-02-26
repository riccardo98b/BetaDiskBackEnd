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
import com.betacom.dischi.repository.IProdottoRepository;
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

	@Autowired
	IProdottoRepository prodottoRepository;
	
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
	
	Integer idProdotto = 1;
	String titolo = "Among the Living";
	String artista = "Anthrax";
	String genere = "Thrash Metal";
	Integer annoPubblicazione = 1987;
	
	@Test
	@Order(1)
	public void createProdottoTest(){
		ProdottoRequest req = createProdottoGeneralRequest();
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
		Assertions.assertThat(response.getMsg()).isEqualTo("Prodotto creato con successo!");
	}
	
	@Test
	@Order(2)
	public void createProdottoConErroreTest(){
		//Creo un doppione, dovrei essere stoppata dal controllo titolo/artista
	ProdottoRequest req = new ProdottoRequest();
		
		
		req.setIdProdotto(10);
		req.setFormato("VINILE");
		req.setTitolo("Among the Living");
		req.setArtista("Anthrax");
		req.setGenere("Un altro genere");
		req.setDescrizione("Un'altra descrizione");
		req.setAnnoPubblicazione(1988);
		req.setPrezzo(28.00);
		req.setQuantita(10);
		req.setImmagineProdotto("https://un'altraimmagine");
		
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(false);	
		
	}
	
	
	@Test
	@Order(3)
	public void listAllProdotti() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, null, null);
	    List<ProdottoDTO> dati = lista.getDati();
		Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
		Assertions.assertThat(lista.getRc()).isEqualTo(true);		
	}
		
	
	@Test
	@Order(4)
	public void listProdottiPerId() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(1, null, null, null, null);
		List<ProdottoDTO> dati = lista.getDati();
		Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
		Assertions.assertThat(dati.stream().anyMatch(t -> t.getIdProdotto().equals(1))).isTrue();
	}
	
	@Test
	@Order(5)
	public void listProdottiPerIdError() throws CustomException{
		ResponseList<ProdottoDTO> lista = prodottoController.listAll(50, null, null, null, null);
		Assertions.assertThat(lista.getRc()).isEqualTo(false);
		
}
	
	@Test
	@Order(6)
	public void listProdottiTitolo() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, titolo, null, null, null);
	    List<ProdottoDTO> dati = lista.getDati();
	    Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
	    Assertions.assertThat(dati.stream().anyMatch(t -> t.getTitolo().equals("Among the Living"))).isTrue();
	}
	
	
	@Test
	@Order(6)
	public void listProdottiTitoloError() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, "Titolo sbagliato", null, null, null);
	    Assertions.assertThat(lista.getRc()).isEqualTo(false);
	 
	}
	
	@Test
	@Order(7)
	public void listProdottiArtista() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, artista, null, null);
	    List<ProdottoDTO> dati = lista.getDati();
	    Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
	    Assertions.assertThat(dati.stream().anyMatch(t -> t.getArtista().equals("Anthrax"))).isTrue();
	}
	
	@Test
	@Order(8)
	public void listProdottiArtistaError() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, "Artista sbagliato", null, null);
		Assertions.assertThat(lista.getRc()).isEqualTo(false);
		
	}
	
	
	
	@Test
	@Order(9)
	public void listProdottiGenere() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, genere, null);
	    List<ProdottoDTO> dati = lista.getDati();
	    Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
	    Assertions.assertThat(dati.stream().anyMatch(t -> t.getGenere().equals("Thrash Metal"))).isTrue();
	}
	
	
	@Test
	@Order(9)
	public void listProdottiGenereError() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, "genere sbagliato", null);
		Assertions.assertThat(lista.getRc()).isEqualTo(false);
	
	}
	
	@Test
	@Order(10)
	public void listProdottiAnnoPubblicazione() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, null, 1987);
	    List<ProdottoDTO> dati = lista.getDati();
	    Assertions.assertThat(dati).isNotEmpty();
		Assertions.assertThat(dati).isNotNull();
	    Assertions.assertThat(dati.stream().anyMatch(t -> t.getAnnoPubblicazione().equals(1987))).isTrue();
	}
	
	
	@Test
	@Order(11)
	public void listProdottiAnnoPubblicazioneError() {

		ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, null, 999);
		Assertions.assertThat(lista.getRc()).isEqualTo(false);
	
	}
	
	
	@Test
	@Order(11)
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
	@Order(12)
	public void updateProdotto() {
		
		ProdottoRequest req = createProdottoGeneralRequest();
		
		req.setTitolo("Ops ho cambiato il titolo");
		
		ResponseBase response = prodottoController.update(req);
		
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		Assertions.assertThat(req.getTitolo()).isEqualTo("Ops ho cambiato il titolo");
		Assertions.assertThat(response.getMsg()).isEqualTo("Prodotto aggiornato con successo!");				
	}
	
	
	@Test
	@Order(13)
	public void updateProdottoError() {
		
		ProdottoRequest req = createProdottoGeneralRequest();
		req.setTitolo("");
		ResponseBase response = prodottoController.update(req);
		Assertions.assertThat(response.getRc()).isEqualTo(false);
		
									
	}
	
	
	
	@Test
	@Order(14)
	public void deleteProdotto() {
	
		ProdottoRequest req = createProdottoGeneralRequest();
		ResponseBase response = prodottoController.delete(req);
		
		Assertions.assertThat(response.getRc()).isEqualTo(true);
		Assertions.assertThat(response.getMsg()).isEqualTo("Prodotto eliminato con successo!");
	}
	
	@Test
	@Order(15)
	public void listProdottiError()throws Exception {
		prodottoRepository.deleteAll(); 
	    ResponseList<ProdottoDTO> lista = prodottoController.listAll(null, null, null, null, null);
	    Assertions.assertThat(lista.getRc()).isFalse();
	}
	
	
	@Test
	@Order(16)
	public void deleteError()throws Exception {
		ProdottoRequest req = createProdottoGeneralRequest();
		prodottoRepository.deleteAll(); 
	    ResponseBase lista = prodottoController.delete(req);
	    Assertions.assertThat(lista.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(17)
	public void listTopTenProdottiError() {
		prodottoRepository.deleteAll(); 
		ResponseList<ProdottoDTO> listaFormati = prodottoController.topTenProdotti();
		Assertions.assertThat(listaFormati.getRc()).isEqualTo(false);	
	}
	
	@Test
	@Order(18)
	public void createProdottoTestPerSuccessivo(){
		ProdottoRequest req = createProdottoGeneralRequest();
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
	}
	
	
	@Test
	@Order(19)
	public void createProdottoTestPerSuccessivo2(){
		ProdottoRequest req = createProdottoGeneralRequest();
		req.setTitolo("Among the Living2");
		
		ResponseBase response = prodottoController.create(req);
		Assertions.assertThat(response.getRc()).isEqualTo(true);	
	}
	
	
	@Test
	@Order(20)
	public void listAllFormati() {
		
		ResponseList<Formato> listaFormati = prodottoController.getFormati();
		Assertions.assertThat(listaFormati.getDati().size()).isGreaterThan(0);	
		Assertions.assertThat(listaFormati.getRc()).isEqualTo(true);	
	}
		

	@Test
	@Order(21)
	public void listTopTenProdotti()throws CustomException {
		
		ResponseList<ProdottoDTO> listaFormati = prodottoController.topTenProdotti();
		Assertions.assertThat(listaFormati.getRc()).isEqualTo(true);	
	}

	

}
