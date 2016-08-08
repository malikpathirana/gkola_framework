package com.gkola.framework.locators;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.gkola.framework.core.UIDriver;
import com.gkola.framework.util.ByUtil;

public class ElementLocatorByDriver extends AbstractElementLocator {
	private static Logger logger = Logger.getLogger(ElementLocatorByDriver.class);
    private UIDriver uiDriver;
	public ElementLocatorByDriver(UIDriver driver,By by) {
		super(by);
		this.uiDriver = driver;
	}

	
	public WebElement locateElement() throws StaleElementReferenceException {
		  if (uiDriver != null && by != null)
	            try {
	                return uiDriver.findElement(by);
	            } catch (Exception ex) {
	                logger.trace(ex);
	            }
	        return null;
	}

	
	public List<WebElement> locateElements() {
		  if (uiDriver != null && by != null) {
	            List<WebElement> elements = uiDriver.findElements(by);
	            if (elements != null && elements.size() > 0)
	                return elements;
	        }
	        return null;
	}
	
	@Override
    public String toString() {
        return ByUtil.getByStr(by);
    }

}
