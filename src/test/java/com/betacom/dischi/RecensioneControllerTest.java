package com.betacom.dischi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.ProdottoController;
import com.betacom.dischi.controller.UtenteController;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.request.ProdottoRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecensioneControllerTest {
	
	@Autowired
	ProdottoController prodottoController;
	@Autowired
	ClienteController clienteController;
	@Autowired
	UtenteController utenteController;
	private ClienteRequest reqCliente;
	private UtenteRequest reqUtente;
	private ProdottoRequest reqProdotto;
	final Integer VALID_ID = 1;
	final Integer INVALID_ID = -2;
	
	//TODO: SERVE ORDINE PER COMPLETARE I TEST
	// cliente
	@BeforeEach
    public void setup() {
        // Inizializzazione prodotto
        reqProdotto = new ProdottoRequest();
        reqProdotto.setFormato("Vinile");
        reqProdotto.setTitolo("Among the Living");
        reqProdotto.setArtista("Anthrax");
        reqProdotto.setGenere("Thrash Metal");
        reqProdotto.setDescrizione("Album iconico del thrash metal, pubblicato nel 1987.");
        reqProdotto.setAnnoPubblicazione(1987);
        reqProdotto.setPrezzo(24.99);
        reqProdotto.setQuantita(8);
        reqProdotto.setImmagineProdotto("https://example.com/among-the-living.jpg");

        ResponseBase responseProdotto = prodottoController.create(reqProdotto);
        assertNotNull(responseProdotto);
        assertEquals(201, responseProdotto.getRc()); // Controllo stato risposta

        reqCliente = new ClienteRequest();
        reqCliente.setNome("Matteo");
        reqCliente.setCognome("Bianchi");
        reqCliente.setImmagineCliente("https://randomuser.me/api/portraits/men/9.jpg");
        reqCliente.setTelefono("3456401123");

        ResponseBase responseCliente = clienteController.create(reqCliente);
        assertNotNull(responseCliente);
        assertEquals(201, responseCliente.getRc());

        reqUtente = new UtenteRequest();
        reqUtente.setEmail("matteob@gmail.com");
        reqUtente.setIdCliente(1);
        reqUtente.setPassword("password");
        reqUtente.setRoles("UTENTE");
        reqUtente.setUsername("matteob");

        ResponseBase responseUtente = utenteController.create(reqUtente);
        assertNotNull(responseUtente);
        assertEquals(201, responseUtente.getRc()); 
    }
}
