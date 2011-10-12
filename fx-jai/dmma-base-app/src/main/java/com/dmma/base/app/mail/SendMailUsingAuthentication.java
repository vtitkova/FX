package com.dmma.base.app.mail;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dmma.base.app.mail.entities.Mail;

public class SendMailUsingAuthentication {
	
	
	private String SMTP_AUTH_PWD = null;
	private String SMTP_AUTH_USER = null;
	public static Log log = LogFactory.getLog(SendMailUsingAuthentication.class);


	public void postMail(Mail mail, String hostName, String hostPort,String hostUser, String hostPwd) throws MessagingException {

		SMTP_AUTH_USER = hostUser;
		SMTP_AUTH_PWD =  hostPwd;
		Boolean is_stmp_auth = true;
		
		boolean debug = true;
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		//Set the host smtp address
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", hostName);
		props.put("mail.smtp.auth", is_stmp_auth?"true":"false");
		props.put("mail.smtp.port", hostPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.quitwait", "false");
		
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);
		msg.setHeader("X-Priority: 1", "");
		msg.setHeader("Content-Type", "text/plain; charset=utf-8");

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(mail.getMailFrom());
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[1];
		for (int i = 0; i < 1; i++) {
			addressTo[i] = new InternetAddress(mail.getMailTo());
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject 
		try {
			msg.setSubject(javax.mail.internet.MimeUtility.encodeText(mail.getSubject(), "UTF-8", "Q"));
		//newMsg.setSubject(javax.mail.internet.MimeUtility.encodeText(subject, "UTF-8", "Q"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			msg.setSubject(mail.getSubject());
		}

		// Setting message content as  Multipart
		Multipart multipart = new MimeMultipart();
		//if(HtmlContent){
			MimeBodyPart msgBodyPartHtml = new MimeBodyPart();
			//msgBodyPartHtml.setContent(messageText, "text/html; charset=utf-8");
			//FIXME msgBodyPartHtml.setContent(HtmlSanitizer.getCleanHTML(messageText), "text/html; charset=utf-8");
			msgBodyPartHtml.setContent(mail.getMessage(), "text/html; charset=utf-8");
			multipart.addBodyPart(msgBodyPartHtml);
		/*}else{
			MimeBodyPart msgBodyPartText = new MimeBodyPart();
			//msgBodyPartText.setContent(messageText, "text/plain; charset=utf-8");
			msgBodyPartText.setContent(TextMsgParser.mailHtmlToTxt(messageText, 80, ">"), "text/plain; charset=utf-8");
			multipart.addBodyPart(msgBodyPartText);		
		}*/

		// Create the message part
		msg.setContent(multipart);
		//msg.setContent(multipart, "charset=utf-8");
		msg.setSentDate(new Date());

		Transport.send(msg);
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
		}
	}
}