package com.betacom.dischi.exception.prodotto;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class ProdottoNotFoundException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public ProdottoNotFoundException() {
		super("Codice errore: "+ErrorCode.PRODOTTO_NOT_FOUND.getCode()+", "+ErrorCode.PRODOTTO_NOT_FOUND.getDescription());
	}




}
