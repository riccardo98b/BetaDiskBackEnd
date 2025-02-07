package com.betacom.dischi.services.interfaces;


import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.WishlistRequest;


public interface WishlistService {

 
    void create(WishlistRequest req) throws Exception;
    
   
    void addProductToWishlist(Integer idCliente, Integer idProdotto) throws CustomException;


   
}
