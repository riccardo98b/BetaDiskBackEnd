package com.betacom.dischi.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.WishlistRequest;
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
    public ResponseBase create(@RequestBody WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        response.setrC(true);  
        try {
            wishlistService.create(req); 
        } catch (CustomException e) {
            response.setrC(false);
            response.setMsg("Wishlist create con successo: " + e.getMessage());
        } catch (Exception e) {  
            response.setrC(false);
            response.setMsg("Errore interno nel server: " + e.getMessage());
        }
        return response;
    }

    @PostMapping("/addProduct")
    public ResponseBase addProduct(@RequestParam Integer idCliente, @RequestParam Integer idProdotto) {
        ResponseBase response = new ResponseBase();
        response.setrC(true);
        try {
            wishlistService.addProductToWishlist(idCliente, idProdotto);
        } catch (CustomException e) {
            response.setrC(false);
            response.setMsg(e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/removeProduct")
    public ResponseBase removeProduct(@RequestParam Integer idCliente, @RequestParam Integer idProdotto) {
        ResponseBase response = new ResponseBase();
        response.setrC(true);
        try {
            wishlistService.removeProductFromWishlist(idCliente, idProdotto);
            response.setMsg("Prodotto rimosso con successo dalla wishlist.");
        } catch (CustomException e) {
            response.setrC(false);
            response.setMsg("Errore durante la rimozione del prodotto: " + e.getMessage());
        } catch (Exception e) {
            response.setrC(false);
            response.setMsg("Errore interno nel server: " + e.getMessage());
        }
        return response;
    }
}
