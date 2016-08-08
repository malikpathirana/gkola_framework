package com.gkola.framework.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public abstract class FWObject {
	private static Logger logger = Logger.getLogger(FWObject.class);
    protected static Config config = DefaultConfig.getConfig();
    protected static String defaultStr = "";
    protected static String TODO = "To be realized ....";
    protected static long verificationCount;
    protected static List<String> verificationElements = new ArrayList<String>();
 // Default timeout for framework to verify an element
    protected static long config_verify_timeout = 0;
    // Default interval time for framework to verify an element
    protected static long config_verify_interval = 1000;
    // Default timeout for framework to cache an element (cache function is removed)
    protected static long config_element_timeout = 0;
    // Default timeout for framework to cache elements (cache function is removed)
    protected static long config_elements_timeout = 0;
    // Default timeout for all waiting function in framework
    protected static long config_wait_timeout = 0;
    // Default timeout for a page to be loaded
    protected static long config_pageload_timeout = 0;

    public FWObject() {
        initConfig();
        setupConfigValue();
    }

    public FWObject(UIDriver driver) {
		// TODO Auto-generated constructor stub
	}

	private void initConfig() {
        try {
            logger.trace("FWNoUIDriverObject.initConfig");
            if (config == null)
                config = DefaultConfig.getConfig();
        } catch (Exception ex) {
            logger.debug(ex);
        }
    }

    private void setupConfigValue() {
        logger.trace("FWNoUIDriverObject.setupConfigValue start");
        config_verify_timeout = config.getLongValue(
                ConfigKeys.KEY_VERIFY_TIMEOUT.getKey(),
                Timeout.VERIFY_TIMEOUT.getValue());
        config_verify_interval = config.getLongValue(
                ConfigKeys.KEY_VERIFY_INTERVAL.getKey(),
                Timeout.VERIFY_INTERVAL.getValue());
        config_element_timeout = config.getLongValue(
                ConfigKeys.KEY_ELEMENT_TIMEOUT.getKey(),
                Timeout.ELEMENT_TIMEOUT.getValue());
        config_elements_timeout = config.getLongValue(
                ConfigKeys.KEY_ELEMENTS_TIMEOUT.getKey(),
                Timeout.ELEMENTS_TIMEOUT.getValue());
        config_wait_timeout = config.getLongValue(
                ConfigKeys.KEY_WAIT_TIMEOUT.getKey(),
                Timeout.WAIT_TIMEOUT.getValue());
        config_pageload_timeout = config.getLongValue(
                ConfigKeys.KEY_PAGELOAD_TIMEOUT.getKey(),
                Timeout.PAGELOAD_TIMEOUT.getValue());
        logger.trace("FWNoUIDriverObject.setupConfigValue end");

    }

    
    public void sleep(long ms) {
        sleep(ms, TimeUnit.MILLISECONDS, "");
    }

    
    public void sleep(long ms, String reason) {
        sleep(ms, TimeUnit.MILLISECONDS, reason);
    }

    
    public void sleep(long time, TimeUnit unit) {
        sleep(time, unit, "");
    }

    
    public void sleep(long time, TimeUnit unit, String reason) {
        if (time > 0)
            try {
                logger.debug("Sleeping for " + time + " " + unit.toString() + ". " + reason);
                unit.sleep(time);
            } catch (InterruptedException e) {
                logger.error("Caught InterruptedException", e);
            }
    }

    public long getVerifyTimeout() {
        return config_verify_timeout;
    }

    public void setVerifyTimeout(long timeout) {
        config_verify_timeout = timeout;
    }

    public long getVerifyInterval() {
        return config_verify_interval;
    }

    public void setVerifyInterval(long timeout) {
        config_verify_interval = timeout;
    }

    public long getElementTimeout() {
        return config_element_timeout;
    }

    public void setElementTimeout(long timeout) {
        config_element_timeout = timeout;
    }

    public long getElementsTimeout() {
        return config_elements_timeout;
    }

    public void setElementsTimeout(long timeout) {
        config_elements_timeout = timeout;
    }

    public long getWaitTimeout() {
        return config_wait_timeout;
    }

    public void setWaitTimeout(long timeout) {
        config_wait_timeout = timeout;
    }
}
