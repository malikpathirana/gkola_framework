package com.gkola.framework.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.gkola.framework.core.FWDriverObject;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIEleBase;
import com.gkola.framework.locators.ElementLocator;

public class FWEleBase extends FWDriverObject implements UIEleBase{
	private static Logger logger = Logger.getLogger(FWEleBase.class);
    protected ElementLocator locator;
    
    protected List<String> replaceValues = new ArrayList<String>();
    protected String description = null;
    protected String originalDescription = null;
    
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocator(ElementLocator locator) {
		this.locator = locator;
	}

	protected FWEleBase(UIDriver driver) {
        super(driver);
        logger.trace("FWEleBase.FWEleBase(1)");
    }

    protected FWEleBase(UIDriver driver, ElementLocator locator) {
        super(driver);
        logger.trace("FWEleBase.FWEleBase(2)");
        this.locator = locator;
    }

    protected FWEleBase(UIDriver driver, ElementLocator locator, String description) {
        super(driver);
        this.locator = locator;
        this.description = description;
        this.originalDescription = description;
    }

	
	public void setUIDriver(UIDriver uiDriver) {
		// TODO Auto-generated method stub
		
	}

	
	public UIDriver getUIDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ElementLocator getLocator() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
