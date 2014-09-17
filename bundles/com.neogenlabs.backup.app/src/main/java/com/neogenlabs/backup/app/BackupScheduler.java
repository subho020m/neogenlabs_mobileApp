package com.neogenlabs.backup.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupScheduler implements Runnable {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public void run() {
		log.info("Hello");

	}

}
