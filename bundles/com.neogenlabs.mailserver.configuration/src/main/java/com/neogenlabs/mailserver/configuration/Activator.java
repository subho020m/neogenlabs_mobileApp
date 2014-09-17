package com.neogenlabs.mailserver.configuration;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import com.neogenlabs.mail.MailService;
import static com.neogenlabs.mailserver.constants.MailConstants.*;

/**
 * This is life cycle activator for configuring the mail service interface
 * @author SUBHAJIT
 *
 */
public class Activator extends DependencyActivatorBase {
	
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
	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {

		manager.add(createComponent()
				.setInterface(MailService.class.getName(), null)
				.setImplementation(MailServiceImpl.class)
				.add(createConfigurationDependency()
						.setPid(CONFIG_PID)
						.setHeading(HEADING)
						.setDescription(DESCRIPTION)
						.add(createPropertyMetaData()
								.setCardinality(0)
								.setType(String.class)
								.setHeading(HEADING_FIELD_1)
								.setDescription(DESCRIPTION_FIELD_1)
								.setDefaults(
										new String[] { DEFAULT_FIELD_1 })
								.setId(ID_FIELD_1))
						.add(createPropertyMetaData()
								.setCardinality(0)
								.setType(String.class)
								.setHeading(HEADING_FIELD_2)
								.setDescription(DESCRIPTION_FIELD_2)
								.setDefaults(
										new String[] { DEFAULT_FIELD_2 })
								.setId(ID_FIELD_2))
						.add(createPropertyMetaData()
								.setCardinality(0)
								.setType(String.class)
								.setHeading(HEADING_FIELD_3)
								.setDescription(DESCRIPTION_FIELD_3)
								.setDefaults(
										new String[] {DEFAULT_FIELD_3 })
								.setId(ID_FIELD_3))
						.add(createPropertyMetaData()
								.setCardinality(0)
								.setType(String.class)
								.setHeading(HEADING_FIELD_4)
								.setDescription(DESCRIPTION_FIELD_4)
								.setDefaults(
										new String[] { DEFAULT_FIELD_4 })
								.setId(ID_FIELD_4))		
						));
	}
}
