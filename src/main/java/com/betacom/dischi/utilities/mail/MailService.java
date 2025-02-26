package com.betacom.dischi.utilities.mail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.request.MailRequest;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class MailService {

	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private PDFGenerator pdfGenerator;

//    public void sendSimpleEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//    
     public void mailConfermaOrdine(MailRequest request) throws Exception {
    	StringBuilder testo = new StringBuilder();
    	testo.append("<h1>Ciao <strong>")
    		.append(request.getNome()).append(" ")
    		.append(request.getCognome()).append("</strong></h1>")
    		.append("<p>Il tuo ordine presso BetaDisk è stato confermato!</p>")
    		.append("<p>Il tuo ordine sarà spedito a breve.</p>")
    		.append("<p>In allegato trovi la ricevuta del tuo ordine</p>")
    		.append("<h3><em>Grazie dal team BetaDisk!</em></h3>");
    	
    	StringBuilder ricevuta = new StringBuilder();
    	ricevuta.append("<h1>Ricevuta BetaDisk</h1>")
    		.append("<br><br><p>Ordine del: <em>" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</em></p>")
    		.append("<br><p> Riepilogo dell'ordine: </p>")
    		.append("<ol>");
    		request.getProdotti().forEach(prodotto -> 
		     	   	ricevuta.append("<li>")
		     	   			.append(prodotto.getProdotto().getTitolo()).append(": € ")
		     	            .append(prodotto.getProdotto().getPrezzo()).append(" x ")
		     	            .append(prodotto.getQuantita()).append("</li>"));
    	ricevuta.append("</ol><br>")
    		.append("<h4>Il totale del tuo ordine è: <strong>€ ")
    		.append(request.getTotale().toString())
    		.append("</strong></h4>");

//    	SimpleMailMessage message = new SimpleMailMessage();
    	MimeMessage msg = mailSender.createMimeMessage();
    	MimeMessageHelper msgHelp = new MimeMessageHelper(msg, true, "UTF-8");
    	byte[] allegato = pdfGenerator.generatePDF(ricevuta.toString());
    		
        msgHelp.setTo(request.getToEmail());
        msgHelp.setSubject("Ordine BetaDisk confermato");
        msgHelp.setText(testo.toString(), true);
        msgHelp.addAttachment("riepilogo.pdf", new ByteArrayDataSource(allegato, "application/pdf"));
        mailSender.send(msg);
     }
     
     public void mailConfermaRegistrazione(MailRequest request) throws CustomException{
    	 StringBuilder testo = new StringBuilder();
    	 
    	 testo.append("Ciao, ")
    	      .append(request.getNome()).append(" ")
    	      .append(request.getCognome()).append(", \n")
    	      .append("La tua registrazione su BetaDisk è andata a buon fine! \n" )
    	      .append("La tua password temporanea è: "+request.getPassword()+" ,ricordati di cambiarla! \n")
    	      .append("Se hai bisogno di assistenza, non esitare a contattarci.\n")
    	      .append( "Grazie per esserti registrato!"
    	      		);
    		SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getToEmail());
            message.setSubject("Registrazione confermata");
            message.setText(testo.toString());
    	    mailSender.send(message);
     }
     public void mailConfermaRegistrazioneAdminNonCliente(MailRequest request) throws CustomException{
    	 StringBuilder testo = new StringBuilder();
    	 testo.append("Ciao, \n ")
    	      .append("La tua registrazione come Admin su BetaDisk è andata a buon fine! \n" )
    	      .append("Il tuo username è: "+request.getUsername()+" \n")
    	      .append("La tua password temporanea è: "+request.getPassword()+" ,ricordati di cambiarla! \n")
    	      .append("Se hai bisogno di assistenza, non esitare a contattarci.\n")
    	      .append( "Grazie per esserti registrato!"
    	      		);
    		SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getToEmail());
            message.setSubject("Registrazione Admin  confermata");
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
