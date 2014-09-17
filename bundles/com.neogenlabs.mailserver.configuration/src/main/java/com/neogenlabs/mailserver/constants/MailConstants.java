package com.neogenlabs.mailserver.constants;

/**
 * This interface provides all the necessary constants to use for configuration in felix web console
 * @author SUBHAJIT
 *
 */
public interface MailConstants {

	public static final String CONFIG_PID                        = "com.neogenlabs.mail.server.conf";
	public static final String HEADING                             = "NeoGen Labs Mail Server Configuration";
	public static final String DESCRIPTION                      = "Configuration for Sending Mail";
	
	public static final String HEADING_FIELD_1               = "SMTP Server";
	public static final String DESCRIPTION_FIELD_1         = "SMTP Server to send mails from";
	public static final String DEFAULT_FIELD_1                = "mail.mailserver.com";
	public static final String ID_FIELD_1                           = "smtp_server";
	
	public static final String HEADING_FIELD_2                = "SMTP Server Port";
	public static final String DESCRIPTION_FIELD_2         = "SMTP  Port to send mails from";
	public static final String DEFAULT_FIELD_2                = "80";
	public static final String ID_FIELD_2                           = "smtp_server_port";
	
	public static final String HEADING_FIELD_3                = "Username";
	public static final String DESCRIPTION_FIELD_3         = "Username to connect to SMTP server";
	public static final String DEFAULT_FIELD_3                = "username";
	public static final String ID_FIELD_3                            = "username";
	
	public static final String HEADING_FIELD_4                = "Password";
	public static final String DESCRIPTION_FIELD_4         = "Password for the username provided above";
	public static final String DEFAULT_FIELD_4                = "password";
	public static final String ID_FIELD_4                            = "password";
}
