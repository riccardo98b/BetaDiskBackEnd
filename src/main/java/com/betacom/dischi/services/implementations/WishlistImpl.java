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
    	
    }
       
}