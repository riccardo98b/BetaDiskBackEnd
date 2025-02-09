package com.betacom.dischi.exception.recensione;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.utilities.enums.ErrorCode;

public class CannotReviewProdottoNotBought extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public CannotReviewProdottoNotBought() {
		super("Codice errore: "+ErrorCode.CANNOT_REVIEW_PRODUCT_NOT_BOUGHT.getCode()+", "+ErrorCode.CANNOT_REVIEW_PRODUCT_NOT_BOUGHT.getDescription());
	}




}
