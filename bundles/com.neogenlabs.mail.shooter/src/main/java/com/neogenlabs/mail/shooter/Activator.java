package com.neogenlabs.mail.shooter;

import java.util.Dictionary;
import java.util.Properties;

import javax.servlet.Servlet;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import com.neogenlabs.mail.MailService;

/**
 * This is life cycle activator for the new order placement
 * @author SUBHAJIT
 *
 */
public class Activator extends DependencyActivatorBase {
	
	/*********************************************************
	 * Constants
	 *********************************************************/
	private static final String DEPENDENCY_ADDED_1 = "bindMailService";
	private static final String DEPENDENCY_CHANGED_1 = "changeMailService";
	private static final String DEPENDENCY_REMOVED_1 = "unbindMailService";

	/*
	 * (non-Javadoc)
	 * @see org.apache.felix.dm.DependencyActivatorBase#destroy(org.osgi.framework.BundleContext, org.apache.felix.dm.DependencyManager)
	 */
	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.felix.dm.DependencyActivatorBase#init(org.osgi.framework.BundleContext, org.apache.felix.dm.DependencyManager)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		Dictionary properties = new Properties();
		
		//The default servlet to access for mail sending
		properties.put("sling.servlet.paths", "/sendMail");
		properties.put("service.description", "This is responsible to send mails");
		
		manager.add(createComponent()
				.setInterface(Servlet.class.getName(), properties)
				.setImplementation(MailShootServlet.class)
				.add(createServiceDependency().setService(MailService.class)
						.setCallbacks(DEPENDENCY_ADDED_1, DEPENDENCY_CHANGED_1, DEPENDENCY_REMOVED_1)));
	}

}
