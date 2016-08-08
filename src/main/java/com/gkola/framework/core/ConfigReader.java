package com.gkola.framework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;




public class ConfigReader {
	 private static Properties configFile;
	 private String path = Default.CONFIG_PATH.getValue();
	 private int varDepth = Default.CONFIG_VAR_DEPTH;
	 private static final Pattern FIND_VARS_PATTERN = Pattern.compile("\\%([a-zA-Z0-9_]+)\\%");

	ConfigReader() {}
    public ConfigReader(String path) {
        this.path = path;
    }

    public Properties readConfig() {
        InputStream istream = getClass().getResourceAsStream(path);
        if (null == istream) {
            try {
                istream = new FileInputStream(path);
            } catch (FileNotFoundException e) {}
            if (null == istream)
                throw new ConfigFileExceptions(path);
        }
        configFile = new java.util.Properties(getDefaultProperties());
        try {
            configFile.load(istream);
            istream.close();
        } catch (Exception ex) {
            throw new ConfigFileExceptions(path);
        }
        //resolveCommandLineArgs();
        //resolveVariables();
        return configFile;
    }

    private Properties getDefaultProperties() {
        Properties def = new Properties();
        def.setProperty(ConfigKeys.KEY_DRIVER_URL.getKey(), String.valueOf(Default.DRIVER_URL
                .getValue()));
        // TODO 
        def.setProperty(ConfigKeys.KEY_OS.getKey(),
                Default.OS.getValue());
        def.setProperty(ConfigKeys.KEY_BROWSER.getKey(),
                Default.DRIVER_BROWSER.getValue());
        def.setProperty(ConfigKeys.KEY_START_PAGE_URL.getKey(),Default.START_PAGE
                .getValue());
        // Timeout
        def.setProperty(ConfigKeys.KEY_WAIT_TIMEOUT.getKey(), String.valueOf(Timeout.WAIT_TIMEOUT
                .getValue()));
        def.setProperty(ConfigKeys.KEY_VERIFY_TIMEOUT.getKey(), String
                .valueOf(Timeout.VERIFY_TIMEOUT.getValue()));
        def.setProperty(ConfigKeys.KEY_VERIFY_INTERVAL.getKey(), String
                .valueOf(Timeout.VERIFY_INTERVAL.getValue()));
        def.setProperty(ConfigKeys.KEY_ELEMENT_TIMEOUT.getKey(), String
                .valueOf(Timeout.ELEMENT_TIMEOUT.getValue()));
        def.setProperty(ConfigKeys.KEY_ELEMENTS_TIMEOUT.getKey(), String
                .valueOf(Timeout.ELEMENTS_TIMEOUT.getValue()));
        def.setProperty(ConfigKeys.KEY_PAGELOAD_TIMEOUT.getKey(), String
                .valueOf(Timeout.PAGELOAD_TIMEOUT.getValue()));
        return def;
    }

}
