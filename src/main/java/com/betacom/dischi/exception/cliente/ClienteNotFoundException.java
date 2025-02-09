package com.betacom.dischi.exception.cliente;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class ClienteNotFoundException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException() {
		super("Codice errore: "+ErrorCode.UTENTE_NOT_FOUND.getCode()+", "+ErrorCode.CLIENTE_NOT_FOUND.getDescription());
	}




}
