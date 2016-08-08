package com.gkola.framework.elements;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;

import com.gkola.framework.core.FrameworkExceptions;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.drivers.InputDevices;

public class DefaultUIActions implements UIActions{

	 private static final Logger logger = Logger.getLogger(DefaultUIActions.class);
	    private UIDriver uiDriver;
	    private Actions builder;
	    private UIElement element;

	    public DefaultUIActions(UIDriver newUIDriver, UIElement uiElement) {
	        this.uiDriver = newUIDriver;
	        this.element = uiElement;
	    }

	    private Actions getActions() {
	        if (builder == null && uiDriver != null)
	            builder = new Actions(uiDriver.getWebDriver());
	        return builder;
	    }

	    public void doubleClick() {
	        logger.debug("Double click on " + element.toString());
	        element.waitToBePresent();
	        this.getActions().doubleClick(element).build().perform();
	    }

	    public void rightClick() {
	        logger.debug("Right click on " + element.toString());
	        element.waitToBePresent();
	        this.getActions().contextClick(element).build().perform();
	    }

	    public void sendKeys(CharSequence... keysToSend) {
	        logger.debug("Send keys '" + Arrays.toString(keysToSend) + "' to element " + element);
	        element.waitToBePresent();
	        this.getActions().sendKeys(element, keysToSend).build().perform();
	    }

	    public void click() {
	        logger.debug("Click on element " + element.toString());
	        element.waitToBePresent();
	        this.getActions().moveToElement(element).click().build().perform();
	    }

	    public void dragAndDrop(UIElement target) {
	        logger.debug("Drag element " + element.toString() + " and drop it to " + target.toString());
	        element.waitToBePresent();
	        target.waitToBePresent();
	        this.getActions().dragAndDrop(element, target).build().perform();
	    }

	    public void dragAndDrop(int xOffset, int yOffset) {
	        logger.debug("Drag and drop element " + element.toString() + " to offset with X = '" + xOffset + "', Y = '" + yOffset + "'");
	        element.waitToBePresent();
	        this.getActions().dragAndDropBy(element, xOffset, yOffset).build().perform();
	    }

	    public void clickOnLocation() {
	        WebDriver driver = uiDriver.getWebDriver();
	        if (driver instanceof HasInputDevices) {
	            Locatable location = (Locatable) element;
	            logger.debug("Clicking on location of " + element.toString());
	            InputDevices device = new InputDevices(uiDriver);
	            ClickAction clicker = new ClickAction(device.getMouse(), location);
	            clicker.perform();
	        } else {
	            throw new FrameworkExceptions("This method is unsupported for driver of type: " + driver);
	        }
	    }

	    public void mouseMoveHere() {
	        logger.debug("Move mouse to the middle of " + element.toString());
	        element.waitToBePresent();
	        this.getActions().moveToElement(element).build().perform();
	    }

	    public void hoverOver() {
	        WebDriver driver = uiDriver.getWebDriver();
	        if (driver instanceof HasInputDevices) {
	            if (uiDriver.hasCapability(CapabilityType.HAS_NATIVE_EVENTS)) {
	                HasInputDevices devices = (HasInputDevices) driver;
	                Locatable location = (Locatable) element;
	                logger.debug("Hover Over " + element.toString());
	                MoveToOffsetAction hover = new MoveToOffsetAction(
	                        devices.getMouse(),
	                        location,
	                        0,
	                        0);
	                hover.perform();
	            } else {
	                throw new FrameworkExceptions("Cannot hover without native events on " + element
	                        .toString());
	            }
	        } else {
	            throw new FrameworkExceptions("This method is unsupported for driver of type: " + driver);
	        }
	    }

	    public void hoverOverAndClickOn() {
	        logger.debug("Move to and click on " + element.toString());
	        element.waitToBePresent();
	        this.getActions().moveToElement(element).click().build().perform();
	    }

	    public void clickAndHold() {
	        logger.debug("Click and hold left mouse btn on " + element.toString());
	        element.waitToBePresent();
	        this.getActions().clickAndHold(element).build().perform();
	    }

	    public void release() {
	        logger.debug("Release left mouse btn on " + element.toString());
	        element.waitToBePresent();
	        this.getActions().release(element).build().perform();
	    }

}
