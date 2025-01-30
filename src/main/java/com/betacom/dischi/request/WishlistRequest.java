package com.betacom.dischi.request;

import java.util.List;

public class WishlistRequest {
	private List<Integer> prodottiId;  
    private Integer clienteId; 

    

	@Override
	public String toString() {
		return "WishListRequest [prodottiId=" + prodottiId + ", clienteId=" + clienteId + ", getProdottiIds()="
				+ getProdottiIds() + ", getClienteId()=" + getClienteId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public List<Integer> getProdottiIds() {
        return prodottiId;
    }

    public void setProdottiIds(List<Integer> prodottiIds) {
        this.prodottiId = prodottiIds;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}