package com.flight_reservation_app.util;

//import java.io.File;
//import java.io.FileInputStream;

//import javax.activation.DataSource;
//import javax.mail.internet.MimeMessage;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;

//@Component
//public class SendEmail {
	

//	 @Autowired
//	    private JavaMailSender emailSender;
	
	
//	public void sendTicket( String to, String subject, String text, String pathToAttachment) {
	    
	    
//	    MimeMessage message = emailSender.createMimeMessage();
	     
//	   try {
	//	   MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    
		//    helper.setFrom("noreply@baeldung.com");
		 //   helper.setTo(to);
		  //  helper.setSubject(subject);
		  //  helper.setText(text);
		        
		  //  FileInputStream file = new FileInputStream(new File(pathToAttachment));
		  //  helper.addAttachment("ticket", (DataSource) file);

		   // emailSender.send(message);
		   
	  // } catch (Exception e) {
		//   System.out.println(e);
		   
	  // }
	    
	//}

//}


import java.io.File;


import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender emailSender;

    public void sendTicket(String to, String subject, String text, String pathToAttachment) {

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            File file = new File(pathToAttachment);
            DataSource dataSource = new FileDataSource(file);
            helper.addAttachment(file.getName(), dataSource);

            emailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

