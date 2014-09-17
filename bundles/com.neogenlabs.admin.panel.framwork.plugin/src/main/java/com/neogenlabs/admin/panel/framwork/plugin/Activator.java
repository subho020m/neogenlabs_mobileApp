package com.neogenlabs.admin.panel.framwork.plugin;

import java.util.Dictionary;
import java.util.Properties;

import javax.servlet.Servlet;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		Dictionary properties = new Properties();
		properties.put("felix.webconsole.label", "Admin Panel");
		
		manager.add(createComponent()
	            .setInterface(Servlet.class.getName(), properties)
	            .setImplementation(AdminPanelConsolePlugin.class)
	        );
	}

}
