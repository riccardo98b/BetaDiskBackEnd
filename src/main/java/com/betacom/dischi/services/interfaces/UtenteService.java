package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.utilities.enums.Roles;

public interface UtenteService {
	
	SignInDTO signIn(SignInRequest req) ;
	
	void createUser(UtenteRequest req) throws CustomException;
	
	List<UtenteDTO> listAll(String username,String email);

	UtenteDTO listById(Integer id) throws CustomException;

	void deleteUtente(Integer id) throws CustomException;
	
	void updateUtente(UtenteRequest req) throws CustomException;

	List<UtenteDTO> listPerRoles(Roles roles) throws CustomException;

	void changePassword(Integer idUtente, String currentPassword, String newPassword) throws CustomException;

	boolean verifyCurrentPassword(Integer id, String currentPassword) throws CustomException;
	
	
}
