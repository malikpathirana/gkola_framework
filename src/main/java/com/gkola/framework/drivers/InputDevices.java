package com.gkola.framework.drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import com.gkola.framework.core.UIDriver;

public class InputDevices implements HasInputDevices{
	  private static final Logger logger = Logger.getLogger(InputDevices.class);
	  private UIDriver uiDriver;
	  
	  public InputDevices(UIDriver uidriver) {
	        this.uiDriver = uidriver;
	    }

	    /**
	     * Get {@link Keyboard}
	     * 
	     * @return {@link Keyboard}
	     */
	    
	    public Keyboard getKeyboard() {
	        HasInputDevices devices = (HasInputDevices) uiDriver.getWebDriver();
	        return devices.getKeyboard();
	    }

	    /**
	     * Get {@link Mouse}
	     * 
	     * @return {@link Mouse}
	     */
	    
	    public Mouse getMouse() {
	        HasInputDevices devices = (HasInputDevices) uiDriver.getWebDriver();
	        return devices.getMouse();
	    }
}
