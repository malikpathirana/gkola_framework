package com.gkola.framework.core;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public abstract class FWDriverObject extends FWObject { 
	 private static Logger logger = Logger.getLogger(FWDriverObject.class);
	 protected UIDriver uiDriver;
	public FWDriverObject(UIDriver driver) {
		 this();
	        logger.trace("FWObject.FWObject(1)");
	        uiDriver = driver;
	}
	public FWDriverObject() {
		super();
        logger.trace("FWObject.FWObject(0)");
	}
	public UIDriver getUiDriver() {
		return uiDriver;
	}
	public void setUiDriver(UIDriver uiDriver) {
		this.uiDriver = uiDriver;
	}
	
	public void logInstruction(String instruction) {
        Reporter.log("&bull; " + instruction + "<br/>");
        logger.info(instruction);
    }
}
