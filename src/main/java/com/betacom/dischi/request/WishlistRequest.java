package com.betacom.dischi.request;

import java.util.List;

public class WishlistRequest {
	private Integer idWishlist;
	private List<Integer> idProdotto;  
    private Integer Idcliente; 

    

	@Override
	public String toString() {
		return "WishListRequest [prodottiId=" + idProdotto + ", clienteId=" + Idcliente + ", getProdottiIds()="
				+ getProdottiIds() + ", getClienteId()=" + getClienteId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

	public Integer getIdWishlist() {
		return idWishlist;
	}



	public void setIdWishlist(Integer idWishlist) {
		this.idWishlist = idWishlist;
	}



	public List<Integer> getIdProdotto() {
		return idProdotto;
	}



	public void setIdProdotto(List<Integer> idProdotto) {
		this.idProdotto = idProdotto;
	}



	public Integer getIdcliente() {
		return Idcliente;
	}



	public void setIdcliente(Integer idcliente) {
		Idcliente = idcliente;
	}



	public List<Integer> getProdottiIds() {
        return idProdotto;
    }

    public void setProdottiIds(List<Integer> prodottiIds) {
        this.idProdotto = prodottiIds;
    }

    public Integer getClienteId() {
        return Idcliente;
    }

    public void setClienteId(Integer clienteId) {
        this.Idcliente = clienteId;
    }
}