package com.betacom.dischi.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.betacom.dischi.services.interfaces.SystemMsgServices;
import com.betacom.dischi.services.interfaces.UtenteService;
import com.betacom.dischi.utilities.enums.Roles;
import jakarta.transaction.Transactional;
import static com.betacom.dischi.utilities.Utility.*;
@Service
public class UtenteImpl implements UtenteService{

	private  IUtenteRepository utenteRepo;
    private  IClienteRepository clienteRepo;
    private  Logger log;
    private  PasswordEncoder passwordEncoder;
    private SystemMsgServices msgServ;

    public UtenteImpl(IUtenteRepository utenteRepo,
                       IClienteRepository clienteRepo,
                       Logger log,
                       PasswordEncoder passwordEncoder,
                       SystemMsgServices msgServ) {
        this.utenteRepo = utenteRepo;
        this.clienteRepo = clienteRepo;
        this.log = log;
        this.passwordEncoder = passwordEncoder;
        this.msgServ = msgServ;
    }

    @Override
    @Transactional
    public SignInDTO signIn(SignInRequest req) {
        log.debug("Signin utente: " + req.getUsername() + " " + req.getPassword());
        SignInDTO resp = new SignInDTO();

        Optional<Utente> utenteOpt = utenteRepo.findByUsername(req.getUsername());
        
        if (utenteOpt.isEmpty()) {
            resp.setLogged(false);
        } else {
            Utente utente = utenteOpt.get();
            
            if (passwordEncoder.matches(req.getPassword(), utente.getPassword())) {
                resp.setLogged(true);
                resp.setRole(utente.getRoles().toString());
                resp.setIdUtente(utente.getIdUtente());

                if (utente.getCliente() != null) {
                    Cliente cliente = utente.getCliente();
                    resp.setIdCliente(cliente.getIdCliente());
                    resp.setDataRegistrazione(cliente.getDataRegistrazione());
                } else {
                    resp.setIdCliente(null);
                    resp.setDataRegistrazione(null);
                }
                
                resp.setUsername(utente.getUsername());
            } else {
                resp.setLogged(false);
            }
        }
        return resp;
    }

	
@Transactional
public boolean verifyCurrentPassword(Integer id, String currentPassword) throws CustomException{
	Utente utente = utenteRepo.findById(id)
			.orElseThrow(() -> new CustomException("Utente non trovato"));
	if(!passwordEncoder.matches(currentPassword, utente.getPassword())) {
		throw new CustomException("Password corrente non corretta");
	}
	return true;
}
@Transactional
@Override
public void changePassword(Integer idUtente, String currentPassword, String newPassword) throws CustomException {
    if (!verifyCurrentPassword(idUtente, currentPassword)) {
        throw new CustomException("Password corrente non corretta");
    }
    
    Utente utente = utenteRepo.findById(idUtente)
            .orElseThrow(() -> new CustomException("Utente non trovato"));

    utente.setPassword(passwordEncoder.encode(newPassword));
    utenteRepo.save(utente);
}



/* è possibile creare un utente legato ad un cliente, altrimenti,
 *  non avendo il cliente, l'utente viene creato senza associarlo 
 *  a un cliente(vale solo per l'admin)*/
@Transactional
@Override
public void createUser(UtenteRequest req) throws CustomException {
    log.debug("Crea utente: " + req);
    Optional<Utente> optUtente = utenteRepo.findByUsername(req.getUsername());
    if (optUtente.isPresent()) {
        throw new CustomException("Utente con questo username già esistente");
    }
    if (req.getRoles() == null) {
        req.setRoles("UTENTE");
    }

    Utente utente = new Utente();

    if (req.getIdCliente() != null) {
        Optional<Cliente> optCliente = clienteRepo.findById(req.getIdCliente());
        if (optCliente.isPresent()) {
            Cliente cliente = optCliente.get();
            if (cliente.getUtente() != null) {
                throw new CustomException("Cliente già associato a un altro utente");
            }
            utente.setCliente(cliente);
            cliente.setUtente(utente);
            clienteRepo.save(cliente); 
        } else {
            throw new CustomException("Cliente non trovato");
        }
    }
    utente.setPassword(passwordEncoder.encode(req.getPassword()));
    utente.setRoles(Roles.valueOf(req.getRoles()));
    utente.setEmail(req.getEmail());
    utente.setUsername(req.getUsername());

    utenteRepo.save(utente);

    
}

	@Override
	public List<UtenteDTO> listAll(String username,String email) {
	    List<Utente> listaUtenti = utenteRepo.filteredUsers(username, email);
	    return listaUtenti.stream()
	            .map(u -> buildUtenteDTO(u))
	            .toList();
	}

	@Transactional
	@Override
	public void deleteUtente(Integer id) throws CustomException{
		log.debug("Cancellazione utente con ID: "+id);
		Utente utente = utenteRepo.findById(id)
				.orElseThrow(() -> new CustomException("Utente non trovato"));
		Cliente cliente = utente.getCliente();
		if(cliente != null) {
			cliente.setUtente(null);
			clienteRepo.save(cliente);
		}
		utenteRepo.delete(utente);

	}

	@Override
	public void updateUtente(UtenteRequest req) throws CustomException{
		log.debug("Aggiornamento utente con ID: "+req.getIdUtente());
	
		Utente utente =  utenteRepo.findById(req.getIdUtente())
				.orElseThrow(() -> new CustomException("Utente non trovato"));
        Optional<Utente> optUtente = utenteRepo.findByUsername(req.getUsername());
		if(!utente.getUsername().equals(req.getUsername())){
			if(optUtente.isPresent()) {
				throw new CustomException("Utente con questo username già esistente");
			}
			utente.setUsername(req.getUsername());
		}
		if(req.getPassword() != null && !req.getPassword().isEmpty()) {
			utente.setPassword(passwordEncoder.encode(req.getPassword()));
		}
		utente.setRoles(Roles.valueOf(req.getRoles()));
        if(req.getRoles() == null || req.getRoles().isEmpty()) {
        	if(utente.getRoles() == Roles.ADMIN) {
        		req.setRoles("ADMIN");
        	}else {
        		req.setRoles("UTENTE");
        	}
        }
		utente.setEmail(req.getEmail());
		utenteRepo.save(utente);

	}

	@Override
	public UtenteDTO listById(Integer id) throws CustomException {
		log.debug("Visualizzazione dati utente con ID: " + id);
	    Utente utente = utenteRepo.findById(id)
	    		.orElseThrow(() -> new CustomException("Utente non trovato"));
		return buildUtenteDTO(utente);
	}

	@Transactional(rollbackOn = CustomException.class)
	@Override
	public List<UtenteDTO> listPerRoles(Roles roles) throws CustomException {

		List<Utente> listaFiltrata = utenteRepo.utentiPerRoles(roles);
		if(listaFiltrata.isEmpty() || listaFiltrata == null)
			throw new CustomException(msgServ.getSysMsg(""));

		List<UtenteDTO> risultato = listaFiltrata.stream()
				.map(p -> buildUtenteDTO(p))
	            .collect(Collectors.toList());

	    return risultato;
	}

}
