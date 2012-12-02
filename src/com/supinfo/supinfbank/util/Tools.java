package com.supinfo.supinfbank.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;

public class Tools
{
	private static SecureRandom random = new SecureRandom();
	
	public static String sha1(String password)
    {
        return DigestUtils.shaHex(password);
    }
	
	public static String generatePassword()
	{
		return new BigInteger(130, random).toString(32);
	}
	
	public static boolean sendEmail(String to, String title, String messageContent)
	{
		String from = "cyril.maitre@supinfo.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		Authenticator authenticator = new Authenticator() 
		{
		    protected PasswordAuthentication getPasswordAuthentication()
		    {
		        return new PasswordAuthentication("supinbank92807@gmail.com", "supinfo92807");
		    }
		};
		Session session = Session.getDefaultInstance(properties, authenticator);

		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(title);
			message.setText(messageContent);
			Transport.send(message);
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
			return false;
		}
		return true;
   }
}
