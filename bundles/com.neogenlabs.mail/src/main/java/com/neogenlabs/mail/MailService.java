package com.neogenlabs.mail;

/**
 * This is a service interface for sending mails
 * @author AMIT
 *
 */
public interface MailService {
	
	/**
	 * Sends mail to the specific server as provided in the configurations
	 * @return true if the mail is successfully sent otherwise false
	 */
	public boolean sendMail(String from, String subject, String message, String addTo, Object attachment);
	
	/**
	 * Sends mail to the specific server as provided in the configurations
	 * @return true if the mail is successfully sent otherwise false
	 */
	public boolean sendMail(String from, String subject, String message, String addTo);

}
