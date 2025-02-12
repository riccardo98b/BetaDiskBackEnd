package com.betacom.dischi.DTO;

import java.util.List;

public class WishlistDTO {
    
    private Integer idWishlist;
    private List<ProdottoDTO> prodotti;
    private ClienteDTO cliente;
    
    public WishlistDTO(Integer idWishlist, List<ProdottoDTO> prodotti, ClienteDTO cliente) {
        this.idWishlist = idWishlist;
        this.prodotti = prodotti;
        this.cliente = cliente;
    }
    
    // Getters and Setters
    public Integer getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(Integer idWishlist) {
        this.idWishlist = idWishlist;
    }

    public List<ProdottoDTO> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ProdottoDTO> prodotti) {
        this.prodotti = prodotti;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "WishlistDTO [idWishlist=" + idWishlist + ", prodotti=" + prodotti + ", cliente=" + cliente + "]";
    }
}
