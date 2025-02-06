package com.betacom.dischi.services.implementations;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.WishlistDTO;

import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Wishlist;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.repository.IWishlistRepository;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.response.ResponseObject;
import com.betacom.dischi.services.interfaces.WishlistService;

import java.util.List;


@Service
public class WishlistImpl implements WishlistService {

    @Autowired
    private IWishlistRepository wishlistRepository;
    
    @Autowired 
    Logger logger;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProdottoRepository prodottoRepository;

    @Override
    public ResponseObject<String> create(WishlistRequest req) {
        ResponseObject<String> response = new ResponseObject<>();

        try {
            Cliente cliente = clienteRepository.findById(req.getIdCliente())
                    .orElseThrow(() -> new Exception("Cliente non trovato"));

            List<Prodotto> prodotti = prodottoRepository.findAllById(req.getIdProdotti());
            
            if (prodotti.size() != req.getIdProdotti().size()) {
                throw new Exception("1 o pi");
            }
            Wishlist wishlist = new Wishlist();
            wishlist.setCliente(cliente);
            wishlist.setProdotti(prodotti);

            wishlistRepository.save(wishlist);

            response.setrC(true);
            response.setMsg("Wishlist creata con successo");
        } catch (Exception e) {
            logger.error("Errore nella creazione wishlist", e);
            response.setrC(false);
            response.setMsg("Error: " + e.getMessage());
        }

        return response;
    }
    @Override
    public List<WishlistDTO> listAll() {
       
        return null;
    }

    @Override
    public void update(WishlistRequest req) throws Exception {
        
    }

    @Override
    public ResponseObject<String> delete(WishlistRequest req) {
        ResponseObject<String> response = new ResponseObject<>();
        
        try {
           
            Wishlist wishlist = wishlistRepository.findById(req.getIdWishlist())
                    .orElseThrow(() -> new Exception("Wishlist not found"));

          
            wishlist.getProdotti().clear();  
           
            if (wishlist.getCliente() != null) {
                wishlist.getCliente().setWishlist(null);  
            }

           
            wishlistRepository.delete(wishlist);
            
            response.setrC(true);
            response.setMsg("Wishlist successfully deleted.");
        } catch (Exception e) {
            logger.error("Error while deleting wishlist", e);
            response.setrC(false);
            response.setMsg("Error: " + e.getMessage());
        }
        
        return response;
    }




    @Override
    public WishlistDTO getById(Integer id) throws Exception {
      
        return null;
    }
}