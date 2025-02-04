package com.betacom.dischi.services.implementations;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.WishlistDTO;
import com.betacom.dischi.DTO.ProdottoDTO;
import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Wishlist;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.repository.IWishlistRepository;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.services.interfaces.WishlistService;

import java.util.List;
import java.util.stream.Collectors;

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
    public void create(WishlistRequest req) throws Exception {
        logger.info("Creazione Wishlist per Cliente ID: {}", req.getClienteId());
        
        Cliente cliente = clienteRepository.findById(req.getClienteId())
            .orElseThrow(() -> new Exception("Cliente non trovato"));

        List<Prodotto> prodotti = prodottoRepository.findAllById(req.getProdottiIds());
        if (prodotti.isEmpty() || prodotti.size() != req.getProdottiIds().size()) {
            throw new Exception("Alcuni prodotti non sono stati trovati nel database");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setCliente(cliente);
        wishlist.setProdotti(prodotti);

        wishlistRepository.save(wishlist);
        logger.info("Wishlist creata con successo per Cliente ID: {}", req.getClienteId());
    }

    @Override
    public void update(WishlistRequest req) throws Exception {
        Wishlist wishlist = wishlistRepository.findById(req.getIdWishlist())
                                              .orElseThrow(() -> new Exception("Wishlist non trovata"));

        List<Prodotto> prodotti = prodottoRepository.findAllById(req.getProdottiIds());
        wishlist.setProdotti(prodotti);

        wishlistRepository.save(wishlist);
    }

    @Override
    public void delete(WishlistRequest req) throws Exception {
        Wishlist wishlist = wishlistRepository.findById(req.getIdWishlist())
                                              .orElseThrow(() -> new Exception("Wishlist non trovata"));

        wishlistRepository.delete(wishlist);
    }

    private WishlistDTO convertToDTO(Wishlist wishlist) {
        List<ProdottoDTO> prodottiDTO = wishlist.getProdotti().stream()
            .map(prodotto -> new ProdottoDTO(prodotto.getIdProdotto(), prodotto.getNome(), prodotto.getPrezzo()))
            .collect(Collectors.toList());

        ClienteDTO clienteDTO = null;
        if (wishlist.getCliente() != null) {
            Cliente cliente = wishlist.getCliente();
            clienteDTO = new ClienteDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getCognome(), cliente.getTelefono(), cliente.getImmagineCliente(), null, null, null, null, null);
        }

        return new WishlistDTO.Builder()
            .idWishlist(wishlist.getIdWishlist())
            .prodotti(prodottiDTO)
            .cliente(clienteDTO)
            .build();
    }

    @Override
    public List<WishlistDTO> listAll() {
        return wishlistRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public WishlistDTO getById(Integer id) throws Exception {
        Wishlist wishlist = wishlistRepository.findById(id)
                                              .orElseThrow(() -> new Exception("Wishlist non trovata"));
        return convertToDTO(wishlist);
    }
}
