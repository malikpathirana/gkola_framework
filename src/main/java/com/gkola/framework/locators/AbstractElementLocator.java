package com.gkola.framework.locators;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.gkola.framework.util.ByUtil;
import com.gkola.framework.util.UIType;

public abstract class AbstractElementLocator implements ElementLocator{
	private static Logger logger = Logger.getLogger(AbstractElementLocator.class);
    protected By by;
    protected String originalValue;
    protected List<String> replaceValues = new ArrayList<String>();
    
    public AbstractElementLocator(By by) {
        this.by = by;
        originalValue = ByUtil.getByValue(by);
    }

   
    public void replaceValues(String... values) {
        replaceValues.clear();
        UIType uiType = ByUtil.getUITypeBy(by);
        String eleValue = originalValue;
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            eleValue = eleValue.replace("{" + (i + 1) + "}", value);
            replaceValues.add(value);
        }
        by = ByUtil.getBy(uiType, eleValue);
    }
}
