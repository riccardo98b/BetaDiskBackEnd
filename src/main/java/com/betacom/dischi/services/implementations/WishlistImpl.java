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
            // Ensure the Cliente exists
            Cliente cliente = clienteRepository.findById(req.getIdCliente())
                    .orElseThrow(() -> new CustomException("Cliente non trovato"));

            // Ensure the Prodotto exists
            List<Prodotto> prodotti = prodottoRepository.findAllById(req.getIdProdotti());

            // Validate if all prodotti are found
            if (prodotti.size() != req.getIdProdotti().size()) {
                throw new CustomException("1 o più prodotti non trovati");
            }

            // Create the Wishlist and associate Cliente and Prodotti
            Wishlist wishlist = new Wishlist();
            wishlist.setCliente(cliente);
            wishlist.setProdotti(prodotti);

            // Save the Wishlist in the database
            wishlistRepository.save(wishlist);

            log.debug("Wishlist creata con successo con ID: " + wishlist.getIdWishlist());

        } catch (Exception e) {
            // Log the error and throw a CustomException
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
            // Troviamo la wishlist esistente con l'ID fornito
            Optional<Wishlist> optWishlist = wishlistRepository.findById(req.getIdWishlist());

            // Se la wishlist non viene trovata, solleviamo un'eccezione
            Wishlist wishlist = optWishlist.orElseThrow(() ->
                new CustomException("Id: " + req.getIdWishlist() + " del wishlist non trovato.")
            );

            // Se il cliente specificato nel request non esiste, solleviamo un'eccezione
            Cliente cliente = clienteRepository.findById(req.getIdCliente())
                    .orElseThrow(() -> new CustomException("Cliente non trovato"));

            // Recuperiamo i prodotti specificati nel request
            List<Prodotto> prodotti = prodottoRepository.findAllById(req.getIdProdotti());

            // Verifica che tutti i prodotti siano stati trovati
            if (prodotti.size() != req.getIdProdotti().size()) {
                throw new CustomException("1 o più prodotti non trovati");
            }

            // Aggiorniamo la wishlist con i nuovi dati
            wishlist.setCliente(cliente);
            wishlist.setProdotti(prodotti);

            // Salviamo la wishlist aggiornata nel database
            wishlistRepository.save(wishlist);

            log.debug("Wishlist con ID " + req.getIdWishlist() + " aggiornata con successo.");

        } catch (Exception e) {
            // Log dell'errore e sollevamento dell'eccezione
            log.error("Errore nell'aggiornamento della wishlist", e);
            throw new CustomException("Errore nell'aggiornamento della wishlist: " + e.getMessage());
        }
    }

    @Override
    public void delete(WishlistRequest req) throws CustomException {
        log.debug("Delete Wishlist: " + req);
        
        // Find the wishlist by its ID using the provided ID from the request
        Optional<Wishlist> optWishlist = wishlistRepository.findById(req.getIdWishlist());
        
        // If the wishlist is not found, throw a custom exception
        Wishlist wishlist = optWishlist.orElseThrow(() -> 
            new CustomException("Id: " + req.getIdWishlist() + " del wishlist non trovato.")
        );
        
        // Delete the wishlist
        wishlistRepository.delete(wishlist);
        
        // Log the successful deletion
        log.debug("Wishlist con Id " + req.getIdWishlist() + " eliminato con successo.");
    }




    @Override
    public WishlistDTO getById(Integer id) throws Exception {
      
        return null;
    }
}