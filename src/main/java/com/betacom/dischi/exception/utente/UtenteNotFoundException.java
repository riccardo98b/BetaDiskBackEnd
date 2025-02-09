package com.betacom.dischi.exception.utente;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class UtenteNotFoundException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public UtenteNotFoundException() {
		super("Codice errore: "+ErrorCode.UTENTE_NOT_FOUND.getCode()+", "+ErrorCode.UTENTE_NOT_FOUND.getDescription());
	}


}
