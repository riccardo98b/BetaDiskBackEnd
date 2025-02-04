package com.betacom.dischi.DTO;

import java.util.List;

public class WishlistDTO {

    private Integer idWishlist;
    private List<ProdottoDTO> prodotti;
    private ClienteDTO cliente;

    private WishlistDTO() {}

    public static class Builder {
        private Integer idWishlist;
        private List<ProdottoDTO> prodotti;
        private ClienteDTO cliente;

        public Builder() {}

        public Builder idWishlist(Integer idWishlist) {
            this.idWishlist = idWishlist;
            return this;
        }

        public Builder prodotti(List<ProdottoDTO> prodotti) {
            this.prodotti = prodotti;
            return this;
        }

        public Builder cliente(ClienteDTO cliente) {
            this.cliente = cliente;
            return this;
        }

        public WishlistDTO build() {
            WishlistDTO dto = new WishlistDTO();
            dto.idWishlist = this.idWishlist;
            dto.prodotti = this.prodotti;
            dto.cliente = this.cliente;
            return dto;
        }
    }

    public Integer getIdWishlist() {
        return idWishlist;
    }

    public List<ProdottoDTO> getProdotti() {
        return prodotti;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }
}
