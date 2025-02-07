package com.betacom.dischi.services.interfaces;


import java.util.List;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.request.WishlistRequest;


public interface WishlistService {

    void create(WishlistRequest req) throws Exception;
    
    void addProductToWishlist(Integer idCliente, Integer idProdotto) throws CustomException;

    void removeProductFromWishlist(Integer idCliente, Integer idProdotto) throws CustomException;

    void clearWishlist(Integer idCliente) throws CustomException;
    
    List<Prodotto> getWishlistProducts(Integer idCliente) throws CustomException;
}
