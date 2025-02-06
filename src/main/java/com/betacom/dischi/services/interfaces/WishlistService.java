package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.response.ResponseObject;

public interface WishlistService {

    List<WishlistDTO> listAll();    

    ResponseObject<String> create(WishlistRequest req) throws Exception;
    ResponseObject<String> delete(WishlistRequest req) throws Exception;
    void update(WishlistRequest req) throws Exception;
    
    //void delete(WishlistRequest req) throws Exception;
    
    WishlistDTO getById(Integer id) throws Exception; 
}
