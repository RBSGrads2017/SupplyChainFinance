package com.rbs.scm.teama_login.utils;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator; 

public class MyMailClass {

	private static String loginTeamUsername = "supplychainmgmt5";
	private static String loginTeamPassword = "password321";
	public static void  sendMail(String customerEmail, String messageToSend, String subjectToSend) {
		
		System.out.println("entered into sending mail class");		
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	
		Session session = Session.getDefaultInstance(props,
			new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication p= 	new PasswordAuthentication(loginTeamUsername,loginTeamPassword );			
				return p;						
				}					
			});
	
		try {
	
			Message message = new MimeMessage(session);
			message.setContent(messageToSend, "text/html; charset=utf-8");
			message.setFrom(new InternetAddress(loginTeamUsername));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(customerEmail));
			message.setSubject(subjectToSend );
			//message.setText(messageToSend);
			Transport.send(message);
			System.out.println("email successfully sent to  "+ customerEmail);
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
