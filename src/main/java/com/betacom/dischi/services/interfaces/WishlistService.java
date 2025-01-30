package com.betacom.dischi.services.interfaces;

import java.util.List;

import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.request.WishlistRequest;

public interface WishlistService {

List<WishlistDTO> listAll();	
	

	
	void create(WishlistRequest req) throws Exception;
		
	 void update(WishlistRequest req) throws Exception;
	    
	    
    
	void delete(WishlistRequest req) throws Exception;
}
