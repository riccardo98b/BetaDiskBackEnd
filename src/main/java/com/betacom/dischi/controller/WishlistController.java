package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.services.interfaces.WishlistService;

@RestController
@RequestMapping("/rest/wishlist")
public class WishlistController {
    
    @Autowired
    Logger log;
    
    @Autowired
    WishlistService wishlistService;
    

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        response.setrC(true);
        log.debug(req.toString());
        try {
            wishlistService.create(req);
            response.setMsg("Wishlist creata con successo!");  
        } catch (Exception e) {
            response.setMsg(e.getMessage());  
            response.setrC(false); 
        }
        return response;
    }

}
