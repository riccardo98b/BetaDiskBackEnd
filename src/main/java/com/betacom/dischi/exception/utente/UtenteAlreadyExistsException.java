package com.betacom.dischi.exception.utente;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class UtenteAlreadyExistsException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public UtenteAlreadyExistsException() {
		super("Codice errore: "+ErrorCode.UTENTE_USERNAME_ALREADY_EXISTS.getCode()+", "+ErrorCode.UTENTE_USERNAME_ALREADY_EXISTS.getDescription());

	}

	
}
