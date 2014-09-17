package com.neogenlabs.admin.panel.framwork.plugin;

import java.io.PrintWriter;

import org.apache.felix.webconsole.ModeAwareConfigurationPrinter;

public class Printer implements ModeAwareConfigurationPrinter {

	public String getTitle() {
		return "Hello";
	}

	public void printConfiguration(PrintWriter writer) {
		writer.print("Hello");
	}

	public void printConfiguration(PrintWriter arg0, String arg1) {

	}

}
