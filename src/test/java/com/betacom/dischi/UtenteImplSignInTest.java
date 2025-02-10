package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.UtenteController;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.services.interfaces.UtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteImplSignInTest {
	
    @Autowired
    UtenteController utenteController;
    
    @Autowired
    ClienteController clienteController;

    @Autowired
    UtenteService utenteService;
    
    private SignInRequest signInRequest;

    final String VALID_USERNAME = "matteob";
    final String INVALID_USERNAME = "ma";
    final String VALID_PASSWORD = "password";
    final String INVALID_PASSWORD = "ppa";
    
	private ClienteRequest reqCliente;
	private UtenteRequest reqUtente;
    
    
    @BeforeEach
    public void setup() {
		reqCliente = new ClienteRequest();
		// cliente
		reqCliente.setNome("Matteo");
		reqCliente.setCognome("Bianchi");
		reqCliente.setImmagineCliente("https://randomuser.me/api/portraits/women/9.jpg");
		reqCliente.setTelefono("3456401123");
		ResponseBase responseCliente = clienteController.create(reqCliente);    	

		// utente
		reqUtente = new UtenteRequest();
		reqUtente.setEmail("matteob@gmail.com");
		reqUtente.setIdCliente(1);
		reqUtente.setPassword("password");
		reqUtente.setRoles("UTENTE");
		reqUtente.setUsername("matteob");
		
		ResponseBase responseUtente = utenteController.create(reqUtente);    	
    	signInRequest = new SignInRequest();
        signInRequest.setUsername(VALID_USERNAME);
        signInRequest.setPassword(VALID_PASSWORD);
        
        
    }
    
    @Test
    @Order(1)
    public void signIn_withValidCredentials_shouldReturnLoggedIn() {
    	signInRequest.setPassword(VALID_PASSWORD);

    	SignInDTO response = utenteService.signIn(signInRequest);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isLogged()).isTrue();
        Assertions.assertThat(response.getRole()).isEqualTo("UTENTE");
    }
    
    @Test
    @Order(2)
    public void signIn_withInvalidUsername_shouldReturnNotLoggedIn() {
    	signInRequest.setUsername(INVALID_USERNAME);
    	SignInDTO response = utenteService.signIn(signInRequest);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isLogged()).isEqualTo(false);
    }
    @Test
    @Order(3)
    public void signIn_withInvalidPassword_shouldReturnNotLoggedIn() {
        // Simula un login con la password sbagliata
        signInRequest.setPassword(INVALID_PASSWORD);
        
        SignInDTO response = utenteService.signIn(signInRequest);
        
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isLogged()).isFalse();
    }
    

}
