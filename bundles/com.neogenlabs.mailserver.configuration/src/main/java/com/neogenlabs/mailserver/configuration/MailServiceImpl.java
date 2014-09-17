package com.neogenlabs.mailserver.configuration;

import static com.neogenlabs.mailserver.constants.MailConstants.HEADING_FIELD_1;
import static com.neogenlabs.mailserver.constants.MailConstants.HEADING_FIELD_2;
import static com.neogenlabs.mailserver.constants.MailConstants.HEADING_FIELD_3;
import static com.neogenlabs.mailserver.constants.MailConstants.HEADING_FIELD_4;
import static com.neogenlabs.mailserver.constants.MailConstants.ID_FIELD_1;
import static com.neogenlabs.mailserver.constants.MailConstants.ID_FIELD_2;
import static com.neogenlabs.mailserver.constants.MailConstants.ID_FIELD_3;
import static com.neogenlabs.mailserver.constants.MailConstants.ID_FIELD_4;
import java.util.Dictionary;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neogenlabs.mail.MailService;

/**
 * The implementation for the mail service interface and the configuration admin for configuring the service
 * @author SUBHAJIT
 *
 */
public class MailServiceImpl implements ManagedService, MailService {
	
	/** Default logger. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*******************************************
	 * The property hold smtp server address
	 *******************************************/
	private String SMTP_SERVER = "smtp.server";
	private String SMTP_PORT = "smtp.port";
	private String SMTP_USERNAME = "username";
	private String SMTP_PASSWORD = "password";

	/*
	 * (non-Javadoc)
	 * @see com.neogenlabs.mail.MailService#sendMail()
	 */
	public boolean sendMail(String from, String subject, String message, String addTo, Object attachment) {
		logger.info("Email Message Sending Started");
		
		if((from != null) && (subject != null) && (message != null) && (addTo != null) && (attachment != null)) {
			try {
				EmailAttachment enclosure = null;
				if( attachment instanceof EmailAttachment) {
					enclosure = (EmailAttachment) attachment;
				}
				MultiPartEmail email = new MultiPartEmail();
				email.setHostName(SMTP_SERVER);
				email.setSmtpPort(Integer.parseInt(SMTP_PORT));
				email.setAuthenticator(new DefaultAuthenticator(SMTP_USERNAME, SMTP_PASSWORD));
				email.setSSLOnConnect(true);
				email.setFrom(from);
				email.setSubject(subject);
				email.setMsg(message);
				email.addTo(addTo);
				email.attach(enclosure);
				email.send();
			} catch (EmailException e) {
				e.printStackTrace();
				return false;
			}
		}
		logger.debug("Email Message Sending Done");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.service.cm.ManagedService#updated(java.util.Dictionary)
	 */
	public void updated(@SuppressWarnings("rawtypes") Dictionary properties) throws ConfigurationException {
		
		if (properties != null) {
			// Getting SMTP Server Property from Config
			SMTP_SERVER = (String) properties.get(ID_FIELD_1);
			if (SMTP_SERVER == null) {
				throw new ConfigurationException(HEADING_FIELD_1,
						"must be specified");
			}
			
			// Getting SMTP Port Property from Config
			SMTP_PORT = (String) properties.get(ID_FIELD_2);
			if (SMTP_PORT == null) {
				throw new ConfigurationException(HEADING_FIELD_2,
						"must be specified");
			}
			
			// Getting SMTP Username Property from Config
			SMTP_USERNAME = (String) properties.get(ID_FIELD_3);
			if (SMTP_USERNAME == null) {
				throw new ConfigurationException(HEADING_FIELD_3,
						"must be specified");
			}
			
			// Getting SMTP Password Property from Config
			SMTP_PASSWORD = (String) properties.get(ID_FIELD_4);
			if (SMTP_PASSWORD == null) {
				throw new ConfigurationException(HEADING_FIELD_4,
						"must be specified");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.neogenlabs.mail.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean sendMail(String from, String subject, String message, String addTo) {
		logger.info("Email Message Sending Started");
		
		if((from != null) && (subject != null) && (message != null) && (addTo != null)) {
			try {
				Email email = new SimpleEmail();
				email.setHostName(SMTP_SERVER);
				email.setSmtpPort(Integer.parseInt(SMTP_PORT));
				email.setAuthenticator(new DefaultAuthenticator(SMTP_USERNAME, SMTP_PASSWORD));
				email.setSSLOnConnect(true);
				email.setFrom(from);
				email.setSubject(subject);
				email.setMsg(message);
				email.addTo(addTo);
				email.send();
			} catch (EmailException e) {
				e.printStackTrace();
				return false;
			}
		}
		logger.debug("Email Message Sending Done");
		return true;
	}
}
