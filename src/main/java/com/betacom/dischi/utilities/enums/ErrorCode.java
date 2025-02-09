package com.betacom.dischi.utilities.enums;

public enum ErrorCode {
	//DEFINISCO QUI CODICE E MESSAGGIO ASSOCIATO PER OGNI ECCEZIONE
    UTENTE_USERNAME_ALREADY_EXISTS("1", "Utente con questo username già esistente"),
    UTENTE_NOT_FOUND("2", "Utente non trovato"),
    CLIENTE_ALREADY_ASSOCIATED("3", "Cliente già associato a un altro utente"),
    CLIENTE_NOT_FOUND("4", "Cliente non trovato"),
    PRODOTTO_NOT_FOUND("5","Prodotto non trovato"),
    RECENSIONE_ALREADY_DONE("6","È possibile creare al massimo una recensione per prodotto"),
    CANNOT_REVIEW_PRODUCT_NOT_BOUGHT("7","Non è possibile recensire un prodotto non acquistato in precedenza");
    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
}
