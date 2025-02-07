package com.betacom.dischi.services.implementations;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.exception.CustomException;
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
import java.util.Optional;


@Service
public class WishlistImpl implements WishlistService {

    @Autowired
    private IWishlistRepository wishlistRepository;
    
    @Autowired 
    Logger log;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProdottoRepository prodottoRepository;

    @Override
    public void create(WishlistRequest req) throws CustomException {
        log.debug("Create Wishlist: " + req);
        
        try {
          
            Cliente cliente = clienteRepository.findById(req.getIdCliente())
                    .orElseThrow(() -> new CustomException("Cliente non trovato"));

           
            List<Prodotto> prodotti = prodottoRepository.findAllById(req.getIdProdotti());

            
            if (prodotti.size() != req.getIdProdotti().size()) {
                throw new CustomException("1 o più prodotti non trovati");
            }

           
            Wishlist wishlist = new Wishlist();
            wishlist.setCliente(cliente);
            wishlist.setProdotti(prodotti);

          
            wishlistRepository.save(wishlist);

            log.debug("Wishlist creata con successo con ID: " + wishlist.getIdWishlist());

        } catch (Exception e) {
          
            log.error("Errore nella creazione della wishlist", e);
            throw new CustomException("Errore nella creazione della wishlist: " + e.getMessage());
        }
    }
    @Override
    public List<WishlistDTO> listAll() {
       
        return null;
    }

    @Override
    public void update(WishlistRequest req) throws CustomException {
        log.debug("Update Wishlist: " + req);

        try {
            
            Optional<Wishlist> optWishlist = wishlistRepository.findById(req.getIdWishlist());

            
            Wishlist wishlist = optWishlist.orElseThrow(() ->
                new CustomException("Id: " + req.getIdWishlist() + " del wishlist non trovato.")
            );

           
            Cliente cliente = clienteRepository.findById(req.getIdCliente())
                    .orElseThrow(() -> new CustomException("Cliente non trovato"));

            
            List<Prodotto> prodotti = prodottoRepository.findAllById(req.getIdProdotti());

            
            if (prodotti.size() != req.getIdProdotti().size()) {
                throw new CustomException("1 o più prodotti non trovati");
            }

           
            wishlist.setCliente(cliente);
            wishlist.setProdotti(prodotti);

            
            wishlistRepository.save(wishlist);

            log.debug("Wishlist con ID " + req.getIdWishlist() + " aggiornata con successo.");

        } catch (Exception e) {
         
            log.error("Errore nell'aggiornamento della wishlist", e);
            throw new CustomException("Errore nell'aggiornamento della wishlist: " + e.getMessage());
        }
    }

    @Override
    public void delete(WishlistRequest req) throws CustomException {
        log.debug("Delete Wishlist: " + req);
     
        Optional<Wishlist> optWishlist = wishlistRepository.findById(req.getIdWishlist());
        
    
        Wishlist wishlist = optWishlist.orElseThrow(() -> 
            new CustomException("Id: " + req.getIdWishlist() + " del wishlist non trovato.")
        );
       
        wishlistRepository.delete(wishlist);
       
        log.debug("Wishlist con Id " + req.getIdWishlist() + " eliminato con successo.");
    }




    @Override
    public WishlistDTO getById(Integer id) throws Exception {
      
        return null;
    }
}