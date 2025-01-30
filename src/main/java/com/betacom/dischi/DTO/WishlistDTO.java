package com.betacom.dischi.DTO;

import java.util.List;

public class WishlistDTO {
	
	 private Integer idWishlist;
	 private List<Integer> prodottiId;  
	 private Integer clienteId; 

	
	public WishlistDTO(Integer idWishlist, List<Integer> prodottiIds, Integer clienteId) {
		super();
		this.idWishlist = idWishlist;
		this.prodottiId = prodottiIds;
		this.clienteId = clienteId;
	}

	public Integer getIdWishlist() {
	    return idWishlist;
	}
	
	public void setIdWishlist(Integer idWishlist) {
	    this.idWishlist = idWishlist;
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
