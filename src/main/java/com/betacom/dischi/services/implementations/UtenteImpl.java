package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Utente;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IUtenteRepository;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.services.interfaces.UtenteService;
import com.betacom.dischi.utilities.Roles;

import jakarta.transaction.Transactional;

@Service
public class UtenteImpl implements UtenteService{

	@Autowired
	private  IUtenteRepository utenteRepo;
	
	@Autowired
	private  IClienteRepository clienteRepo;
	
	@Autowired
	private Logger log;
	

	
	public UtenteImpl() {}

	@Override
	public SignInDTO signIn(SignInRequest req) {
		log.debug("Signin utente: "+req.getUsername());
		SignInDTO resp = new SignInDTO();
		Optional<Utente> utente = utenteRepo.findByUsernameAndPassword(req.getUsername(), req.getPassword());
		if(utente.isEmpty()) {
			resp.setLogged(false);
		}
		else {
			resp.setLogged(true);
			resp.setRole(utente.get().getRoles().toString());
		}
		return resp;
	}

	@Transactional
	@Override
	public void createUser(UtenteRequest req) throws CustomException {
	 log.debug("Crea utente: "+req);
     Optional<Utente> optUtente = utenteRepo.findByUsername(req.getUsername());	
     if(optUtente.isPresent()) {
    	 throw new CustomException("Username esiste gia");
    	 
     }
     if(req.getRoles() == null) {
    	 req.setRoles("UTENTE");
     }
     Optional<Cliente> optCliente = clienteRepo.findById(req.getIdCliente());
     if(optCliente.isEmpty()) {
    	 throw new CustomException("Cliente con ID: "+req.getIdCliente()+ "non trovato");
     }
     Cliente cliente = optCliente.get();
     if(cliente.getUtente() != null) {
    	 throw new CustomException("Cliente gia associato ad un altro utente");

     }
     Utente utente = new Utente();
     utente.setPassword(req.getPassword());
     utente.setRoles(Roles.valueOf(req.getRoles()));
     utente.setEmail(req.getEmail());
     utente.setUsername(req.getUsername());
     utente.setCliente(cliente);
     //se cliente e gia legato a dati di un altro utente allora lancio
     // eccezione
     utenteRepo.save(utente);

     cliente.setUtente(utente);
     clienteRepo.save(cliente);
	}

	@Override
	public List<UtenteDTO> listAll() {
	    List<Utente> listaUtenti = utenteRepo.findAll();
	    return listaUtenti.stream()
	            .map(u -> new UtenteDTO.Builder()
	                    .setIdUtente(u.getIdUtente())
	                    .setUsername(u.getUsername())
	                    .setRoles(u.getRoles().toString()) 
	                    .setEmail(u.getEmail())
	                    .setCliente(null)  // 
	                    .build())
	            .collect(Collectors.toList());
	}

}
