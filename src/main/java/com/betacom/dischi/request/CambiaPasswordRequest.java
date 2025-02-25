package com.betacom.dischi.request;

public class CambiaPasswordRequest {
    private Integer idUtente;
    private String passwordCorrente;
    private String nuovaPassword;

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getPasswordCorrente() {
        return passwordCorrente;
    }

    public void setPasswordCorrente(String passwordCorrente) {
        this.passwordCorrente = passwordCorrente;
    }

    public String getNuovaPassword() {
        return nuovaPassword;
    }

    public void setNuovaPassword(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }
}
