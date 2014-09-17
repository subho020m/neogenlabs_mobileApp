package com.neogenlabs.orderTest.mailShooter;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.event.EventPropertiesMap;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.neogenlabs.mail.MailService;

/**
 * This is responsible to handle mail transfers for new orders or requests creation
 * 
 * @author SUBHAJIT
 *
 */
public class NewOrderPlacedHandler implements EventHandler {

	/** Default logger. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The Resource Resolver Factory Class */
	private volatile ResourceResolverFactory resolverFactory;

	/** The Service Injection for Mail Service */
	private volatile MailService mailService;

	/** The Node where orders and requests are created */
	public static final String ORDER_NODE = "/NeoGenAppMobile/Requests_And_Orders/";

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

	/**
	 * ResourceResolverFactory {@link ResourceResolverFactory} Injection Bound
	 */
	protected void bindResourceResolver(ServiceReference reference,
			ResourceResolverFactory resolverFactory) {
		logger.info("ResourceResolverFactory Injected");
		this.resolverFactory = resolverFactory;
	}

	/**
	 * ResourceResolverFactory {@link ResourceResolverFactory} Injection Changed
	 */
	protected void changeResourceResolver(ServiceReference reference,
			ResourceResolverFactory resolverFactory) {
		logger.info("ResourceResolverFactory Injection Reference Changed");
		this.resolverFactory = resolverFactory;
	}

	/**
	 * ResourceResolverFactory {@link ResourceResolverFactory} Injection Unbound
	 */
	protected void unbindResourceResolver(ServiceReference reference,
			ResourceResolverFactory resolverFactory) {
		logger.info("ResourceResolverFactory Removed");
		if (this.resolverFactory == resolverFactory) {
			this.resolverFactory = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.event.EventHandler#handleEvent(org.osgi.service.event
	 * .Event)
	 */
	@SuppressWarnings("unused")
	public void handleEvent(Event event) {

		logger.debug("New Order Placed for");

		ResourceResolver adminResolver = null;
		String from = null;
		String subject = null;
		String message = null;
		String to = null;
		String resourcePath = null;
		String resourceName = null;

		Adaptable adaptable = null;
		try {
			if (resolverFactory != null) {
				adminResolver = resolverFactory
						.getAdministrativeResourceResolver(null);
				adaptable = resolverFactory
						.getAdministrativeResourceResolver(null);
			}

			// The event map holding the properties of the event
			EventPropertiesMap eventPropertiesMap = new EventPropertiesMap(
					event);

			if (eventPropertiesMap.containsKey(SlingConstants.PROPERTY_PATH)) {
				resourcePath = (String) eventPropertiesMap
						.get(SlingConstants.PROPERTY_PATH);

				if (resourcePath != null && resourcePath.contains(ORDER_NODE)) {
					// All three different ways to access a resource
					final Resource res = adminResolver
							.getResource(resourcePath);
					final ValueMap map = res.adaptTo(ValueMap.class);
					final ValueMap properties = ResourceUtil.getValueMap(res);

					// Get all the properties of the resource
					if (properties != null) {
						from = (String) properties.get("from");
						subject = (String) properties.get("subject");
						message = (String) properties.get("message");
						to = (String) properties.get("to");
					}
					
					if( (from != null) && (subject != null) && (message != null) && (to != null)) {
						mailService.sendMail(from, subject, message, to);
					}
				}
			}

		} catch (final Exception e) {
			logger.error("Exception: " + e, e);
		} finally {
			if (adminResolver != null) {
				adminResolver.close();
			}
		}

	}

}
