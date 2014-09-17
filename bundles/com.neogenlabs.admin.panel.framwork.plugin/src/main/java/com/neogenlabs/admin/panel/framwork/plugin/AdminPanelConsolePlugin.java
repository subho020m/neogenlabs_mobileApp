package com.neogenlabs.admin.panel.framwork.plugin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.felix.webconsole.AbstractWebConsolePlugin;

public class AdminPanelConsolePlugin extends AbstractWebConsolePlugin {

	@Override
	public String getLabel() {
		return "Admin Panel";
	}

	@Override
	public String getTitle() {
		return "Content Admin Panel";
	}

	@Override
	protected void renderContent(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.sendRedirect("/admin_panel/index.html");
	}

}
