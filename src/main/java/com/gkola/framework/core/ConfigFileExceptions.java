package com.gkola.framework.core;

import org.apache.log4j.Logger;

public class ConfigFileExceptions extends FrameworkExceptions{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConfigFileExceptions.class);
    private static String exMessage = "Config file exception: ";

    public ConfigFileExceptions(String path) {
        super(exMessage + path);
    }

    public ConfigFileExceptions(String path, Throwable cause) {
        super(exMessage + path, cause);
    }
}
