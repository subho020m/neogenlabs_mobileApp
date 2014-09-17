package com.neogenlabs.mail.shooter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neogenlabs.mail.MailService;

/**
 * This bundle is responsible for sending mail when new orders and requests are
 * created
 * 
 * @author SUBHAJIT
 *
 */
public class MailShootServlet extends SlingSafeMethodsServlet {

	private final Logger logger = LoggerFactory
			.getLogger(MailShootServlet.class);

	/*
	 * Mail Service Reference
	 */
	private volatile MailService mailService;

	/**
	 * Mail Service {@link MailService} Injection Bound
	 */
	protected void bindMailService(ServiceReference reference,
			MailService mailService) {
		logger.info("Mail Service Injected");
		this.mailService = mailService;
	}

	/**
	 * Mail Service {@link MailService} Injection Changed
	 */
	protected void changeMailService(ServiceReference reference,
			MailService mailService) {
		logger.info("Mail Service Injection Reference Change");
		this.mailService = mailService;
	}

	/**
	 * Mail Service {@link MailService} Injection Unbound
	 */
	protected void unbindMailService(ServiceReference reference,
			MailService mailService) {
		logger.info("Mail Service Removed");
		if (this.mailService == mailService) {
			this.mailService = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache
	 * .sling.api.SlingHttpServletRequest,
	 * org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		HttpServletRequest req = null;
		HttpServletResponse res = null;

		if (request instanceof HttpServletRequest)
			req = (HttpServletRequest) request;

		if (response instanceof HttpServletResponse)
			res = (HttpServletResponse) response;

		if (req != null) {
			String from = (String) req.getParameter("from");
			String to = (String) req.getParameter("to");
			String message = (String) req.getParameter("message");
			String subject = (String) req.getParameter("subject");

			if (from != null && to != null && message != null
					&& subject != null) {
				if (mailService != null) {
					logger.debug("Mail Sending Started");
					mailService.sendMail(from, subject, message, to);
					logger.debug("Mail Sending Stopped");
				}
			}
		}

	}
}
