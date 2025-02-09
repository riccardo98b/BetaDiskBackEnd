package com.betacom.dischi.exception.recensione;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class RecensioneAlreadyDoneException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public RecensioneAlreadyDoneException() {
		super("Codice errore: "+ErrorCode.RECENSIONE_ALREADY_DONE.getCode()+", "+ErrorCode.RECENSIONE_ALREADY_DONE.getDescription());
	}




}
