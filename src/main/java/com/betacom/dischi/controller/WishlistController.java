package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseList;
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

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true) WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        response.setrC(true);
        log.debug(req.toString());
        try {
            wishlistService.update(req);
        } catch (Exception e) {
            response.setMsg(e.getMessage());
            response.setrC(false);
        }        
        return response;
    }
    
    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody(required = true) WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        try {
            wishlistService.delete(req);
            response.setrC(true); 
            response.setMsg("Wishlist eliminata con successo!");
        } catch (Exception e) {
            response.setrC(false); 
            response.setMsg(e.getMessage());
        }
        
        if (response.getrC() == null) {
            response.setrC(false);  
        }
        return response;
    }
    
    @GetMapping("/listAll")
    public ResponseList<WishlistDTO> list() {
        log.debug("Lista di tutte le wishlist: ");
        ResponseList<WishlistDTO> response = new ResponseList<WishlistDTO>();
        response.setrC(true);
        try {
            response.setDati(wishlistService.listAll()); 
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setMsg(e.getMessage());
            response.setrC(false);
        }
        return response;
    }

}
