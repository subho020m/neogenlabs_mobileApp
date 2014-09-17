package sling.bundle.extender;

import org.apache.felix.utils.extender.AbstractExtender;
import org.apache.felix.utils.extender.Extension;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class Extender extends AbstractExtender {

	@Override
	protected void debug(Bundle arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Extension doCreateExtension(Bundle arg0) throws Exception {
		return new BundleExtension(FrameworkUtil.getBundle(sling.sling.bundle.SimpleDSComponent.class));
	}

	@Override
	protected void error(String arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void warn(Bundle arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

}
