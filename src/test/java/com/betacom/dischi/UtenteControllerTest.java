package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.controller.ClienteController;
import com.betacom.dischi.controller.UtenteController;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
import com.betacom.dischi.services.interfaces.UtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {


	@Autowired
	ClienteController clienteController;

	@Autowired
	UtenteController utenteController;
	
	  @Autowired
	    UtenteService utenteService;
	// convenzione nomi metodi test: nomeMetodo_condizione_risultatoAspettato

	private ClienteRequest reqCliente;
	private UtenteRequest reqUtente;
    private SignInRequest reqSignIn;
	final Integer VALID_ID = 2;
	final Integer INVALID_ID = -2;

	 final String VALID_USERNAME = "matteob";
	 final String INVALID_USERNAME = "ma";
	 final String VALID_PASSWORD = "password";
	 final String INVALID_PASSWORD = "ppa";


	@Test
	@Order(1)
	public void createUtente_withValidData_shouldCreateUtente()  {
        reqUtente = new UtenteRequest();

		reqUtente.setEmail("matteob@gmail.com");
		reqUtente.setIdCliente(2);
		reqUtente.setPassword("password");
		reqUtente.setRoles("UTENTE");
		reqUtente.setUsername("matteob");
		ResponseBase responseUtente = utenteController.create(reqUtente);
		System.out.println("id utente trovato:"+reqUtente.getIdUtente());
		  Assertions.assertThatCode(() -> utenteController.create(reqUtente))
          .doesNotThrowAnyException();		}	
   
    @Test
    @Order(2)
    public void signIn_withValidCredentials_shouldReturnLoggedIn()  {
    SignInRequest signInRequest = new SignInRequest();
    signInRequest.setUsername(VALID_USERNAME);
    signInRequest.setPassword(VALID_PASSWORD);
    SignInDTO response = utenteController.signIn(signInRequest);
    System.out.println("Role: " + response.getRole());
    System.out.println("Is logged in: " + response.isLogged());

    Assertions.assertThat(response.isLogged()).isTrue();
    
    Assertions.assertThat(response.getRole()).isEqualTo("UTENTE");


    }
    
    @Test
    @Order(3)
    public void signIn_withInvalidUsername_shouldReturnNotLoggedIn() {
        reqSignIn = new SignInRequest();

    	reqSignIn.setUsername(INVALID_USERNAME);
    	SignInDTO response = utenteService.signIn(reqSignIn);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isLogged()).isEqualTo(false);
    }
    @Test
    @Order(4)
    public void signIn_withInvalidPassword_shouldReturnNotLoggedIn() {
        reqSignIn = new SignInRequest();

        // Simula un login con la password sbagliata
    	reqSignIn.setPassword(INVALID_PASSWORD);
        
        SignInDTO response = utenteService.signIn(reqSignIn);
        
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isLogged()).isFalse();
    }
    
	@Test
	@Order(5)
	public void listAllUtente_shouldReturnListOfUtente() {
		ResponseList<UtenteDTO> responseList = utenteController.list( null, null);
	    Assertions.assertThat(responseList).isNotNull();
	}
	
	

	@Test
	@Order(6)
	public void updateUtente_withNoChanges_shouldReturnSuccess() {
	    reqUtente = new UtenteRequest();
	    reqUtente.setIdUtente(1);
	    reqUtente.setEmail("matteob@gmail.com");  
	    reqUtente.setIdCliente(VALID_ID);
	    reqUtente.setPassword("password");
	    reqUtente.setRoles("UTENTE");
	    reqUtente.setUsername("matteobianco");  

	    ResponseBase response = utenteController.update(reqUtente);
	    Assertions.assertThat(response.getRc()).isEqualTo(true);  
	}
	
	
	@Test
	@Order(7)
	public void updateUtente_withInvalidParameters_shouldThrowException() {
	    reqUtente = new UtenteRequest();
	    reqUtente.setIdUtente(1);
	    reqUtente.setEmail("marco....");  
	    reqUtente.setIdCliente(VALID_ID);
	    reqUtente.setPassword("password");
	    reqUtente.setRoles("UTENTEss");
	    reqUtente.setUsername("matteobianco");

	    Assertions.assertThatExceptionOfType(CustomException.class)
	            .isThrownBy(() -> utenteController.update(reqUtente));
	}
	
	
	@Test
	@Order(8)
	public void listById_withValidId_shouldReturnUtente()  {
		ResponseBase response = utenteController.listById(VALID_ID);
		  Assertions.assertThatCode(() -> utenteController.listById(VALID_ID))
          .doesNotThrowAnyException();		}
	@Test
	@Order(9)
	public void listById_withInvalidId_shouldThrowException() {
		Assertions.assertThatExceptionOfType(CustomException.class)
		.isThrownBy(() -> utenteController.listById(INVALID_ID));
	}
	@Test
	@Order(10)
	public void deleteUtente_withInvalidId_shouldNotDeleteUtente() {
	    reqUtente = new UtenteRequest(); 
	    reqUtente.setIdUtente(INVALID_ID); 

	    Assertions.assertThatExceptionOfType(CustomException.class)
	        .isThrownBy(() -> utenteController.delete(reqUtente));

	
	}
	@Test
	@Order(11)
	public void deleteUtente_withValidId_shouldDeleteUtente() {
	    reqUtente = new UtenteRequest(); 
	    reqUtente.setIdUtente(VALID_ID); 
	    ResponseBase response = utenteController.delete(reqUtente);
	    Assertions.assertThatCode(() -> utenteController.delete(reqUtente))
	          .doesNotThrowAnyException();
	}

}
