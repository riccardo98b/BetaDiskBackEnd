package com.betacom.dischi.services.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dischi.DTO.ClienteDTO;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.request.ClienteRequest;
import com.betacom.dischi.services.interfaces.ClienteService;

@Service
public class ClienteImpl implements ClienteService {

	// aggiungere un campo data iscrizione
	// se cliente ha x anni di iscrizione ha 5% di sconto
	@Autowired
	Logger log;
	
	@Autowired
	IClienteRepository clienteRepo;
	@Override
	public List<ClienteDTO> listAll() {
		return null;
	}

	@Override
	public void create(ClienteRequest req) throws CustomException {
		log.debug("Create Cliente: " + req);
		Cliente cliente = new Cliente();
		checkAndSetFields(req, cliente);
		log.debug("Cliente creato con ID: " + cliente.getIdCliente());
		clienteRepo.save(cliente);
	}

	// aggiornabile eccetto la data di iscrrizione
	@Override
	public void update(ClienteRequest req) throws CustomException {
		log.debug("Update Cliente: " + req);
	    Optional<Cliente> optCliente = clienteRepo.findById(req.getIdCliente());   
	    Cliente cliente = optCliente.orElseThrow(() -> 
		new CustomException("Id: "+ req.getIdCliente()+ " del cliente non trovato."));
        checkAndSetFields(req,cliente);
	    clienteRepo.save(cliente);
	    log.debug("Cliente con Id "+req.getIdCliente()+" aggiornato.");
	}

	@Override
	public void delete(ClienteRequest req) throws CustomException {
		log.debug("Delete Cliente: "+req);
		Optional<Cliente> optCliente = clienteRepo.findById(req.getIdCliente());
		Cliente cliente = optCliente.orElseThrow(() ->
		new CustomException("Id: "+req.getIdCliente()+" del cliente da eliminare non trovato"));
		clienteRepo.delete(cliente);
		log.debug("Cliente con Id "+req.getIdCliente()+ " eliminato con successo.");
	}
	
	// sapere che ordini ha un cliente
	
	// che carrello ha
	
	private void checkAndSetFields(ClienteRequest req, Cliente cliente) throws CustomException {
		if (req.getNome() == null || req.getNome().isBlank()) {
		    throw new CustomException("Il nome non può essere nullo o vuoto");
		}
		if (req.getCognome() == null || req.getCognome().isBlank()) {
		    throw new CustomException("Il cognome non può essere nullo o vuoto");
		}
		if (req.getTelefono() == null || req.getTelefono().isBlank()) {
		    throw new CustomException("Il numero di telefono non può essere nullo o vuoto");
		}
		String telefonoRegex = "^[0-9]{10}$"; // num di tel 10 cifre
		if (!req.getTelefono().matches(telefonoRegex)) {
		    throw new CustomException("Numero di telefono non valido,deve essere di 10 cifre");
		}
		cliente.setNome(req.getNome());
		cliente.setCognome(req.getCognome());
		cliente.setTelefono(req.getTelefono());
		cliente.setImmagineCliente(req.getImmagineCliente());
		cliente.setDataRegistrazione(LocalDate.now());
	}
	

}
