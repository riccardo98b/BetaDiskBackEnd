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
}
