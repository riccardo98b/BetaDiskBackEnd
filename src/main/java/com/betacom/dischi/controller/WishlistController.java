package com.betacom.dischi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Wishlist;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.response.ResponseBase;
import com.betacom.dischi.response.ResponseObject;
import com.betacom.dischi.services.interfaces.WishlistService;
@CrossOrigin(origins="*")
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
        try {
            wishlistService.create(req);  // Attempt to create the wishlist
            response.setRc(true);  // If successful, set rc to true
            response.setMsg("Wishlist create con successo");  // Success message
        } catch (CustomException e) {
            response.setRc(false);  // Set rc to false if there's a CustomException
            response.setMsg("Errore durante la creazione della wishlist: " + e.getMessage());  // Correct error message
        } catch (Exception e) {
            response.setRc(false);  // Set rc to false for any unexpected exception
            response.setMsg("Errore interno nel server: " + e.getMessage());  // Internal server error message
        }
        return response;
    }


    @PostMapping("/addProduct")
    public ResponseBase addProduct(@RequestBody WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        response.setRc(true);
        try {
            wishlistService.addProductToWishlist(req.getIdCliente(), req.getIdProdotti().get(0)); // Assuming 1 product per request
            response.setMsg("Prodotto aggiunto alla wishlist con successo.");
        } catch (CustomException e) {
            response.setRc(false);
            response.setMsg(e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/removeProduct")
    public ResponseBase removeProduct(@RequestBody WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        try {
            // Log the input to ensure correct product and client IDs are received
            log.info("Removing product for client: {} with product ID: {}", req.getIdCliente(), req.getIdProdotti());

            // Passiamo idCliente e idProdotti (primo prodotto) al servizio
            wishlistService.removeProductFromWishlist(req.getIdCliente(), req.getIdProdotti().get(0));

            response.setRc(true);
            response.setMsg("Prodotto rimosso dalla wishlist con successo.");
        } catch (CustomException e) {
            response.setRc(false);
            response.setMsg("Errore durante la rimozione del prodotto: " + e.getMessage());
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg("Errore interno nel server: " + e.getMessage());
        }
        // Log the response to see the outcome
        log.info("Response: {}", response);
        return response;
    }


    
    @PostMapping("/clearWishlist")
    public ResponseBase clearWishlist(@RequestBody WishlistRequest req) {
        ResponseBase response = new ResponseBase();
        response.setRc(true);
        try {
            wishlistService.clearWishlist(req.getIdCliente());
            response.setMsg("Tutti i prodotti sono stati rimossi dalla wishlist.");
        } catch (CustomException e) {
            response.setRc(false);
            response.setMsg("Errore durante lo svuotamento della wishlist: " + e.getMessage());
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg("Errore interno nel server: " + e.getMessage());
        }
        return response;
    }

    
    @GetMapping("/getAllProducts")
    public ResponseObject<List<ProdottoDTO>> getAllProducts(@RequestParam Integer idCliente) {
        ResponseObject<List<ProdottoDTO>> response = new ResponseObject<>();
        response.setRc(true);
        try {
            List<ProdottoDTO> prodotti = wishlistService.getWishlistProducts(idCliente);
            response.setMsg("Prodotti della wishlist recuperati con successo.");
            response.setDati(prodotti); 
        } catch (CustomException e) {
            response.setRc(false);
            response.setMsg("Errore durante il recupero dei prodotti dalla wishlist: " + e.getMessage());
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg("Errore interno nel server: " + e.getMessage());
        }
        return response;
    }



    @GetMapping("/searchById")
    public ResponseObject<Wishlist> searchWishlistById(@RequestParam Integer idWishlist) {
        ResponseObject<Wishlist> response = new ResponseObject<>();
        response.setRc(true);
        try {
            Optional<Wishlist> wishlist = wishlistService.searchWishlistById(idWishlist);
            if (wishlist.isPresent()) {
                response.setDati(wishlist.get());
                response.setMsg("Wishlist trovata con successo.");
            } else {
                response.setRc(false);
                response.setMsg("Wishlist non trovata.");
            }
        } catch (CustomException e) {
            response.setRc(false);
            response.setMsg(e.getMessage());
        }
        return response;
    }
}
