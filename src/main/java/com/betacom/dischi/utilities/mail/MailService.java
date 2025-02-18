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
        message.setFrom("rimosso@rimosso.com"); // lo stesso indirizzo email usato per la configurazione

        mailSender.send(message);
    }
    
     public void mailConfermaOrdine(MailRequest request) throws CustomException {
    	 SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(request.getTo());
         message.setSubject("Ordine BetaDisk confermato");
         String text = "Il tuo ordine presso BetaDisk è stato confermato";
         message.setText(text);
         message.setFrom("betadisk@betacom.com"); // lo stesso indirizzo email usato per la configurazione

         mailSender.send(message);
     }
}
