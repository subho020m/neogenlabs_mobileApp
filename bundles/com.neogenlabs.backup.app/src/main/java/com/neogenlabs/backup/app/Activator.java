package com.neogenlabs.backup.app;

import java.util.Dictionary;
import java.util.Properties;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		Dictionary properties = new Properties();
		properties.put("scheduler.expression", "0 * * * * ?");

		manager.add(createComponent()
				.setInterface(Runnable.class.getName(), properties)
				.setImplementation(BackupScheduler.class));
	}
}
