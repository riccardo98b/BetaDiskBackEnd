package com.betacom.dischi.services.interfaces;


import com.betacom.dischi.request.WishlistRequest;


public interface WishlistService {

    //List<WishlistDTO> listAll();    

    //void update(WishlistRequest req) throws Exception;
    void create(WishlistRequest req) throws Exception;
    
    //void delete(WishlistRequest req) throws Exception;
    
    //WishlistDTO getById(Integer id) throws Exception; 
}
