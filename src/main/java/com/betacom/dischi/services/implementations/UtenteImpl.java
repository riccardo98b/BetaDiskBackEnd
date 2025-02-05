package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.SignInDTO;
import com.betacom.dischi.DTO.UtenteDTO;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Utente;
import com.betacom.dischi.repository.IUtenteRepository;
import com.betacom.dischi.request.SignInRequest;
import com.betacom.dischi.request.UtenteRequest;
import com.betacom.dischi.services.interfaces.UtenteService;
import com.betacom.dischi.utilities.Roles;

@Service
public class UtenteImpl implements UtenteService{

	@Autowired
	private  IUtenteRepository utenteRepo;
	
	@Autowired
	private Logger log;
	
	public UtenteImpl() {}

	@Override
	public SignInDTO signIn(SignInRequest req) {
		log.debug("");
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

	@Override
	public void createUser(UtenteRequest req) throws Exception {
	 log.debug("Crea utente: "+req);
     Optional<Utente> optUtente = utenteRepo.findByUsername(req.getUsername());	
     if(optUtente.isPresent()) {
    	 throw new Exception("Username esiste gia");
    	 
     }
     
     Utente utente = new Utente();
     utente.setPassword(req.getPassword());
     utente.setRoles(Roles.valueOf(req.getRoles()));
     utente.setEmail(req.getEmail());
     utente.setUsername(req.getUsername());
     Cliente cliente = utente.getCliente();
     
     //se cliente e gia legato a dati di un altro utente allora lancio
     // eccezione
     utente.setCliente(cliente);
     utenteRepo.save(utente);
	}

	@Override
	public List<UtenteDTO> listAll() {
	    List<Utente> listaUtenti = utenteRepo.findAll();
	    return listaUtenti.stream()
	            .map(u -> new UtenteDTO.Builder()
	                    .setIdUtente(u.getIdUtente())
	                    .setUsername(u.getUsername())
	                    .setPassword(u.getPassword())
	                    .setRoles(u.getRoles().toString()) 
	                    .setEmail(u.getEmail())
	                    .setCliente(null)  // 
	                    .build())
	            .collect(Collectors.toList());
	}

}
