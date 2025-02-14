package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;

public interface UtenteService {
	
	SignInDTO signIn(SignInRequest req) ;
	
	void createUser(UtenteRequest req) throws CustomException;
	
	List<UtenteDTO> listAll(Integer idUtente,String username,String email);

	UtenteDTO listById(Integer id) throws CustomException;

	void deleteUtente(Integer id) throws CustomException;
	
	void updateUtente(UtenteRequest req) throws CustomException;
	
	
}
