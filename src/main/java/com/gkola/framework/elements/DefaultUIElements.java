package com.gkola.framework.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.core.UIElements;
import com.gkola.framework.locators.ElementLocator;
import com.gkola.framework.locators.ElementLocatorBySelf;

public class DefaultUIElements extends FWEleBase implements UIElements {

	 private static Logger logger = Logger.getLogger(DefaultUIElements.class);

	 private List<WebElement> elements;
	public DefaultUIElements(UIDriver driver, ElementLocator locator) {
		super(driver, locator);
		// TODO Auto-generated constructor stub
	}

	
    public int length() {
        logger.debug("Getting length for " + this.toString());
        int length = -1;
        waitToBePresent();
        if (elements != null)
            length = elements.size();
        logger.debug("Got length: " + length + " for " + this.toString());
        return length;
    }

    public UIElement getUIElementByIndex(int index) {
        logger.debug("Getting element by index:" + index);
        UIElement element = null;
        waitToBePresent();
        if (elements != null && elements.size() > 0 && elements.size() > index) {
            WebElement desiredElement = elements.get(index);
            element = new DefaultUIElement(
                    uiDriver,
                    new ElementLocatorBySelf(desiredElement),
                    uiDriver.getXPath(desiredElement));
        }
        logger.debug((element == null ? "Element not found" : "Found element:") + this.toString());
        return element;
    }

    
    public UIElement getUIElementByText(String text) {
        logger.debug("Getting element by text:" + text);
        UIElement element = null;
        waitToBePresent();
        if (elements != null && elements.size() > 0) {
            WebElement found = null;
            for (WebElement ele : elements) {
                String eleText = ele.getText();
                if (eleText.contains(text)) {
                    found = ele;
                    break;
                }
            }
            element = (found == null ? null : new DefaultUIElement(
                    uiDriver,
                    new ElementLocatorBySelf(found),
                    text));
        }
        logger.debug((element == null ? "Element not found" : "Found element:") + this.toString());
        return element;
    }

    public UIElement getChildUIElementByIndex(int index, By childBy) {
        UIElement element = getUIElementByIndex(index);
        return element == null ? null : element.findUIElement(childBy);
    }

    public List<UIElement> getChildUIElementsByIndex(int index, By childBy) {
        UIElement element = getUIElementByIndex(index);
        return element == null ? null : element.findUIElements(childBy);
    }

    
    public UIElement getChildUIElementByText(String text, By childBy) {
        UIElement element = getUIElementByText(text);
        return element == null ? null : element.findUIElement(childBy);
    }

    
    public List<UIElement> getChildUIElementsByText(String text, By childBy) {
        UIElement element = getUIElementByText(text);
        return element == null ? null : element.findUIElements(childBy);
    }

    
    public List<UIElement> getAllChildUIElements(By childBy) {
        List<UIElement> children = new ArrayList<UIElement>();
        List<UIElement> uiElements = getUIElementsList();
        for (UIElement uiEle : uiElements) {
            UIElement ele = uiEle.findUIElement(childBy);
            if (ele != null)
                children.add(ele);
        }
        return children.size() == 0 ? null : children;
    }

    
    public List<UIElement> getUIElementsList() {
        waitToBePresent();
        if (elements == null) {
            return null;
        } else {
            List<UIElement> elementList = new ArrayList<UIElement>();
            for (WebElement ele : elements) {
                String description = null;
                String innerText = null;
                // trying to find good description for an element
                try {
                    innerText = ele.getText();
                    if (innerText == null | innerText.equals("")) {
                        // Disable getXPath() because it wastes a lot of time
                        // when the list has a big amount of elements.
                        // description = uiDriver.getXPath(ele);
                        description = "";
                    } else {
                        description = "Inner text = '" + innerText + "'";
                    }
                } catch (Exception e) {
                    // we don't really care about description, its fine to set it to null
                }

                elementList.add(new DefaultUIElement(
                        uiDriver,
                        new ElementLocatorBySelf(ele),
                        description));
            }
            return elementList;
        }
    }

    
    public boolean areAllDisplayed() {
        if (!this.areInitialized()) {
            initializeAll();
        }
        boolean result = true;
        if (this.areInitialized()) {
            for (WebElement element : elements) {
                try {
                    if (!element.isDisplayed()) {
                        logger.debug("Element " + this.toString() + " is not displayed");
                        result = false;
                        break;
                    }
                } catch (StaleElementReferenceException ex) {
                    elements = null;
                    result = false;
                    logger.debug(ex.toString());
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    
    public boolean doFindAll() {
        this.initializeAll();
        return this.areInitialized();
    }

    
    public UIElements replaceValues(String... values) {
        locator.replaceValues(values);
        waitToBePresent();
        return this;
    }

    
    public void waitForNumberOfElementsToBePresent(int numberOfElements) {
        waitForNumberOfElementsToBePresent(numberOfElements, config_wait_timeout);
    }

    
    public void waitForNumberOfElementsToBePresent(int numberOfElements, long timeout) {
        long stopTime = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < stopTime) {
            this.doFindAll();
            if (elements != null && elements.size() >= numberOfElements) {
                return;
            }
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        this.doFindAll();
        if (elements != null && elements.size() >= numberOfElements) {
            return;
        }
        throw new TimeoutException(
                "Failed while waiting for " + numberOfElements + " elements to be present under " + this
                        .toString());
    }

    
    public boolean areAllPresent() {
        try {
            return doFindAll();
        } catch (TimeoutException toe) {
            return false;
        }
    }

    
    public void initializeAll() {
        elements = locator.locateElements();
        if (elements == null) {
            logger.debug("Failed to find UI Elements" + this.toString());
        }
    }

    
    public boolean areInitialized() {
        return (elements == null) ? false : true;
    }

    
    public void waitToBePresent() {
        this.waitToBePresent(config_verify_timeout);
    }

    
    public void waitToBePresent(long timeout) {
        long stopTime = System.currentTimeMillis() + timeout;
        while (!this.areAllPresent() && System.currentTimeMillis() < stopTime) {
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        this.areAllPresent();
        if (elements == null) {
            throw new TimeoutException("Failed to find elements under " + this.toString());
        }
    }

    
    public void waitToBeDisplayed(long timeout) {
        long stopTime = System.currentTimeMillis() + timeout;
        while (!areAllDisplayed() && System.currentTimeMillis() < stopTime) {
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        areAllDisplayed();
        if (!this.areAllDisplayed()) {
            throw new TimeoutException("Not all of the elements are displayed under " + this
                    .toString());
        }
    }

    
    public void waitToBeDisplayed() {
        this.waitToBeDisplayed(config_wait_timeout);
    }

    
    public void waitToBeHidden(long timeout) {
        long stopTime = System.currentTimeMillis() + timeout;
        while (areAllPresent() && areAllDisplayed() && System.currentTimeMillis() < stopTime) {
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        if (this.areAllDisplayed()) {
            throw new TimeoutException("Not all elements are hidden under " + this.toString());
        }
    }

    
    public void waitToBeHidden() {
        this.waitToBeHidden(config_wait_timeout);
    }

    
    public void waitToBeAbsent(long timeout) {
        long stopTime = System.currentTimeMillis() + timeout;
        while (areAllPresent() && System.currentTimeMillis() < stopTime) {
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        areAllPresent();
        if (!(elements == null)) {
            throw new TimeoutException("Not all elements are absent under " + this.toString());
        }
    }

    
    public void waitToBeAbsent() {
        waitToBeAbsent(config_wait_timeout);
    }

    
    public String toString() {
        String desc = getDescription();
        String elementLocator;

        if (!locator.toString().equals("")) {
            elementLocator = locator.toString();
        } else {
            elementLocator = "";
        }
        if (desc != null && elementLocator != null) {
            return "[Description = {" + desc + "}, Locator = {" + elementLocator + "}]";
        } else
            if (elementLocator != null) {
                return "[Locator = " + elementLocator + "]";
            } else
                if (desc != null) {
                    return "[Description = " + desc + "]";
                } else {
                    return "";
                }
    }

    
    public UIElement getOneDisplayedElement() {
        return getOneDisplayedElement(config_wait_timeout);
    }

    
    public UIElement getOneDisplayedElement(long timeout) {
        WebElement ele = null;
        long start = System.currentTimeMillis();
        while (ele == null) {
            for (WebElement element : elements)
                if (element.isDisplayed()) {
                    ele = element;
                    break;
                }
            if (ele != null || System.currentTimeMillis() - start > timeout)
                break;
            if (timeout < config_verify_interval) {
                sleep(timeout);
            } else {
                sleep(config_verify_interval);
            }
        }
        for (WebElement element : elements)
            if (element.isDisplayed()) {
                ele = element;
                break;
            }
        return ele == null ? null : new DefaultUIElement(
                uiDriver,
                new ElementLocatorBySelf(ele),
                description);
    }
}
