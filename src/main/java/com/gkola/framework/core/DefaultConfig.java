package com.gkola.framework.core;

import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;




public class DefaultConfig implements Config{
	private static Logger logger = Logger.getLogger(DefaultConfig.class);
    private Properties configFile;

    private static Config config = null;

    public DefaultConfig() {
        ConfigReader reader = new ConfigReader();
        try {
            configFile = reader.readConfig();
        } catch (ConfigFileExceptions ex) {
            logger.debug(ex);
        }
    }

    /**
     * 
     * @param path
     *            to config file
     */
    public DefaultConfig(String path) {
        ConfigReader reader = new ConfigReader(path);
        try {
            configFile = reader.readConfig();
        } catch (ConfigFileExceptions ex) {
            logger.debug(ex);
            throw ex;
        }

    }

  
    public DefaultConfig(Properties properties) {
        configFile = properties;
    }

    public static Config getConfig() {
        if (config == null)
            config = new DefaultConfig();
        return config;
    }

    public static Config getConfig(String path) {
        if (config == null)
            config = new DefaultConfig(path);
        return config;
    }

    public String getValue(String key) {
        String value = configFile.getProperty(key);
        return (value == null) ? "" : value;
    }

    public void setValue(String key, String value) {
        configFile.setProperty(key, value);
    }

    public Long getLongValue(String key, long defaultValue) {
        String longStr = getValue(key).trim();
        logger.trace("Getting config value for " + key);
        if (longStr == null || "".equals(longStr)) {
            logger.trace(" config key " + key + " was not set. Returning default value=" + defaultValue);
            return defaultValue;
        } else {
            logger.trace(" config key " + key + " found");
            long foundValue = Long.parseLong(longStr);
            logger.trace("Config " + key + "=" + foundValue);
            return foundValue;
        }
    }

    public void printAll() {
        for (Entry<Object, Object> entry : configFile.entrySet()) {
            logger.debug(entry.getKey() + "=" + entry.getValue());
        }
    }
}
