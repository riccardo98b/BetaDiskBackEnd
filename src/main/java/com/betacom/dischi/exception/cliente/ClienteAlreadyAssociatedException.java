package com.betacom.dischi.exception.cliente;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class ClienteAlreadyAssociatedException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public ClienteAlreadyAssociatedException() {
		super("Codice errore: "+ErrorCode.CLIENTE_ALREADY_ASSOCIATED.getCode()+", "+ErrorCode.CLIENTE_ALREADY_ASSOCIATED.getDescription());
	}


}
