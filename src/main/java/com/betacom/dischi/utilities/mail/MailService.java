package com.betacom.dischi.utilities.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.MailRequest;

@Service
public class MailService {

	@Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
     public void mailConfermaOrdine(MailRequest request) throws CustomException {
    	StringBuilder text = new StringBuilder();
    	text.append("Ciao ")
    		.append(request.getNome()).append(" ")
    		.append(request.getCognome()).append(",\n")
    		.append("Il tuo ordine presso BetaDisk è stato confermato! \n Ecco il riepilogo del tuo ordine: \n");
    	request.getProdotti().forEach(prodotto -> 
    	   text.append("- ").append(prodotto.getProdotto().getTitolo()).append(": € ")
    	            .append(prodotto.getProdotto().getPrezzo()).append(" x ")
    	            .append(prodotto.getQuantita()).append("\n")
    	);
    	text.append("Il totale del tuo ordine è: ")
    		.append(request.getTotale().toString())
    		.append("\n");
    	text.append("Il tuo ordine sarà spedito a breve. \n");
    	text.append("Grazie dal team BetaDisk!");
    	 
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getToEmail());
        message.setSubject("Ordine BetaDisk confermato");
        message.setText(text.toString());
        //message.setFrom("betadisk@betacom.com");
        mailSender.send(message);
     }
     
     public void mailConfermaRegistrazione(MailRequest request) throws CustomException{
    	 StringBuilder testo = new StringBuilder();
    	 
    	 testo.append("Ciao, ")
    	      .append(request.getNome()).append(" ")
    	      .append(request.getCognome()).append(", \n")
    	      .append("La tua registrazione su BetaDisk è andata a buon fine! \n" )
    	      .append("Se hai bisogno di assistenza, non esitare a contattarci.\n")
    	      .append( "Grazie per esserti registrato!"
    	      		);
    		SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getToEmail());
            message.setSubject("Registrazione confermata");
            message.setText(testo.toString());
    	    mailSender.send(message);
     }
     
     public void mailSpedizioneOrdine(MailRequest request) throws CustomException {
     	StringBuilder text = new StringBuilder();
     	text.append("Ciao ")
     		.append(request.getNome()).append(" ")
     		.append(request.getCognome()).append(",\n")
     		.append("Il tuo ordine del ")
     		.append(request.getDataOrdine()).append("\n")
     		.append("presso BetaDisk è stato spedito!")
     		.append("\n")
     		.append("Grazie dal team BetaDisk!");
     	 
     	SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(request.getToEmail());
         message.setSubject("Ordine BetaDisk spedito");
         message.setText(text.toString());
         mailSender.send(message);
      }
}
