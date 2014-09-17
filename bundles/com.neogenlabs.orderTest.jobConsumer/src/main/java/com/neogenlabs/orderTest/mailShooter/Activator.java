package com.neogenlabs.orderTest.mailShooter;

import java.util.Dictionary;
import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.neogenlabs.mail.MailService;

/**
 * This is life cycle activator for the new order placement event handler
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
	
	private static final String DEPENDENCY_ADDED_2 = "bindResourceResolver";
	private static final String DEPENDENCY_CHANGED_2 = "changeResourceResolver";
	private static final String DEPENDENCY_REMOVED_2 = "unbindResourceResolver";

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
		
		//Listens to the event of new node creation
		properties.put(EventConstants.EVENT_TOPIC,
				SlingConstants.TOPIC_RESOURCE_ADDED);

		manager.add(createComponent()
				.setInterface(EventHandler.class.getName(), properties)
				.setImplementation(NewOrderPlacedHandler.class)
				.add(createServiceDependency().setService(MailService.class)
						.setCallbacks(DEPENDENCY_ADDED_1, DEPENDENCY_CHANGED_1, DEPENDENCY_REMOVED_1))
				.add(createServiceDependency().setService(
						ResourceResolverFactory.class)
						.setCallbacks(DEPENDENCY_ADDED_2, DEPENDENCY_CHANGED_2, DEPENDENCY_REMOVED_2)));
	}

}
