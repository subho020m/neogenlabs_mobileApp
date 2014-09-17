package com.neogenlabs.rested.framework.plugin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.felix.webconsole.AbstractWebConsolePlugin;

public class MyConsolePlugin extends AbstractWebConsolePlugin {

	@Override
	public String getLabel() {
		return "Node Explorer";
	}

	@Override
	public String getTitle() {
		return "Sling Node Explorer";
	}

	@Override
	protected void renderContent(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.sendRedirect("/.edit.html");
	}

}
