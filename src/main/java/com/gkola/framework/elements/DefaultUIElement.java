package com.gkola.framework.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.drivers.ErrorDetector;
import com.gkola.framework.drivers.NewErrorDetector;
import com.gkola.framework.locators.ElementLocator;
import com.gkola.framework.locators.ElementLocatorBySelf;
import com.gkola.framework.util.ByUtil;
import com.gkola.framework.util.SearchScope;
import com.gkola.framework.util.UIType;

public class DefaultUIElement extends FWEleBase implements UIElement{
	 private static Logger logger = Logger.getLogger(DefaultUIElement.class);
	    private WebElement element;
	    private static final ErrorDetector DUMMY_ERROR_DETECTOR = new NewErrorDetector();

	    public DefaultUIElement(UIDriver driver, ElementLocator locator) {
	        this(driver, locator, null);
	    }

	    public DefaultUIElement(UIDriver uiDriver, ElementLocator locator, String description) {
	        super(uiDriver,  locator, description);
	    }
	   

		public UIElement findUIElement(By by) {
	        return findUIElement(by, null, config_wait_timeout);
	    }

	    public UIElement findUIElement(By by, long timeout) {
	        return findUIElement(by, null, timeout);
	    }

	    public UIElement findUIElement(By by, String description) {
	        return findUIElement(by, description, config_wait_timeout);
	    }

	    public UIElement findUIElement(By by, String description, long timeout) {
	        WebElement ele = null;
	        long stopTime = System.currentTimeMillis() + timeout;
	        logger.trace("Try to find " + description + " using " + by + " for " + timeout + " milliseconds");
	        while (System.currentTimeMillis() < stopTime) {
	            ele = findElement(by);
	            if (ele != null)
	                break;
	            if (config_verify_interval > 0) {
	                if (timeout < config_verify_interval) {
	                    sleep(timeout);
	                } else {
	                    sleep(config_verify_interval);
	                }
	            }
	        }
	        ele = findElement(by);

	        return ele == null ? null : new DefaultUIElement(
	                uiDriver,
	                new ElementLocatorBySelf(ele),
	                description);
	    }

	    public List<UIElement> findUIElements(By by) {
	        return findUIElements(by, null, config_wait_timeout);
	    }

	    public List<UIElement> findUIElements(By by, long timeout) {
	        return findUIElements(by, null, timeout);
	    }

	    public List<UIElement> findUIElements(By by, String description) {
	        return findUIElements(by, description, config_wait_timeout);
	    }

	    public List<UIElement> findUIElements(By by, String description, long timeout) {
	        List<WebElement> elements = null;
	        long stopTime = System.currentTimeMillis() + timeout;
	        while (System.currentTimeMillis() < stopTime) {
	            elements = findElements(by);
	            if (elements != null && elements.size() > 0)
	                break;
	            if (config_verify_interval > 0) {
	                if (timeout < config_verify_interval) {
	                    sleep(timeout);
	                } else {
	                    sleep(config_verify_interval);
	                }
	            }
	        }
	        elements = findElements(by);
	        if (elements == null || elements.size() == 0)
	            return null;
	        List<UIElement> elementList = new ArrayList<UIElement>();
	        for (WebElement ele : elements) {
	            elementList.add(new DefaultUIElement(
	                    uiDriver,
	                    new ElementLocatorBySelf(ele),
	                    description));
	        }
	        return elementList;
	    }

	    public WebElement findElement(By by) {
	        WebElement ele = null;
	        initialize();
	        try {
	            if (element != null) {
	                ele = element.findElement(by);
	            }
	        } catch (NoSuchElementException ex) {
	            logger.trace(ex);
	        }
	        logger.debug(ele == null
	                                ? "Failed to find element " + ByUtil.getByStr(by)
	                                : "Found element " + ByUtil.getByStr(by));
	        return ele;
	    }

	    public List<WebElement> findElements(By by) {
	        logger.debug("Looking for elements by: " + ByUtil.getByStr(by));
	        List<WebElement> list = null;
	        initialize();
	        if (element != null) {
	            list = element.findElements(by);
	        }
	        if (list == null) {
	            logger.debug("Found 0 elements by " + ByUtil.getByStr(by));
	        } else {
	            logger.debug("Found " + list.size() + " elements by: " + ByUtil.getByStr(by));
	            for (WebElement item : list) {
	                logger.trace(item.toString());
	            }
	        }
	        return list;
	    }

	    public WebElement getWebElement() {
	        initialize();
	        return element;
	    }

	    public UIElement replaceValues(String... values) {
	        locator.replaceValues(values);
	        if (description != null) {
	            description = originalDescription;
	            for (int i = 0; i < values.length; i++) {
	                String value = values[i];
	                description = description.replace("{" + (i + 1) + "}", value);
	            }
	        }
	        return this;
	    }

	    
	    public void initialize() {
	        element = locator.locateElement();
	        if (element == null) {
	            logger.info("Failed to find UI Element " + this.toString());
	        }
	    }

	    public boolean isInitialized() {
	        return (element == null) ? false : true;
	    }

	    
	    public boolean isAbsent() {
	        return !doFind();
	    }

	    
	    public boolean isPresent() {
	        return doFind();
	    }

	    
	    public void click() {
	        try {
	            waitToBeDisplayed();
	        } catch (TimeoutException e) {
	            String errorMessage = "Failed to click on element " + this.toString() + " because this element is not displayed. If desired behavior is to click on element which is not displayed, and continue without exception please use clickNoWait() method ! Previous Exception: " + e
	                    .getMessage();
	            logger.error(errorMessage);
	            throw new TimeoutException(errorMessage);
	        }
	        if (isEnabled()) {
	            try {
	                element.click();
	            } catch (WebDriverException ex) {
	                if (ex.getMessage().contains("Element is not clickable at point")) {
	                    // Exception likely caused by div hiding element, try again after scrolling
	                    logger.debug("Browser can't click element, scrolling and clicking again");
	                    scrollIntoView();
	                    element.click();
	                }
	            }
	            logger.debug("Clicked on element: '" + this.toString() + "'");
	        } else {
	            logger.warn("Element(" + this.toString() + ") is not clickable!");
	        }
	    }

	    
	    public void clickByJavascript() {
	        try {
	            waitToBeDisplayed();
	        } catch (TimeoutException e) {
	            String errorMessage = "Failed to click on element " + this.toString() + " because this element is not displayed. If desired behavior is to click on element which is not displayed, and continue without exception please use clickNoWait() method ! Previous Exception: " + e
	                    .getMessage();
	            logger.error(errorMessage);
	            throw new TimeoutException(errorMessage);
	        }
	        super.uiDriver.executeScript("arguments[0].click();", element);
	        logger.debug("Clicked on element: '" + this.toString() + "'");
	    }

	    
	    public void clickNoWait() {
	        if (isDisplayed() && isEnabled()) {
	            try {
	                element.click();
	            } catch (WebDriverException ex) {
	                if (ex.getMessage().contains("Element is not clickable at point")) {
	                    // Exception likely caused by div hiding element, try again after scrolling
	                    logger.debug("Browser can't click element, scrolling and clicking again");
	                    scrollIntoView();
	                    element.click();
	                }
	            }
	            logger.debug("Clicked on element: '" + this.toString() + "'");
	        } else {
	            logger.warn("Element(" + this.toString() + ") is not clickable!");
	        }
	    }

	    
	    public void submit() {
	        waitToBeDisplayed();
	        logger.debug("Submitting element: " + this.toString());
	        element.submit();
	    }

	    
	    public void sendKeys(CharSequence... keysToSend) {
	        waitToBeDisplayed();
	        try {
	            logger.debug("Sending keys: " + Arrays.toString(keysToSend) + " to " + this.toString());
	            element.sendKeys(keysToSend);
	        } catch (StaleElementReferenceException ex) {
	            element = null;
	            logger.error(ex);
	        }
	    }

	    
	    public void sendKeysToFileInput(CharSequence... keysToSend) {
	        waitToBePresent();
	        try {
	            logger.debug("Sending keys: " + Arrays.toString(keysToSend) + " to " + this.toString());
	            element.sendKeys(keysToSend);
	        } catch (StaleElementReferenceException ex) {
	            element = null;
	            logger.error(ex);
	        }
	    }

	    
	    public void sendKeys(Keys key) {
	        waitToBeDisplayed();
	        logger.debug("Sending key: " + key + " to " + this.toString());
	        element.sendKeys(key);
	    }

	    /**
	     * This method accomplishes the same goal as clearAndSendKeys but with extensive fault
	     * tollerence, checking and retrying. Use this when the textbox has trouble clearing. Useful for
	     * when chromedriver does not allow sendKeys() to work:
	     * <link>https://code.google.com/p/chromedriver/issues/detail?id=35</link>
	     * 
	     * @param textToEnter
	     */
	    public void clearAndSendKeysRobustly(String textToEnter) {

	        try {
	            waitToBeDisplayed();

	            // Clearing an empty text box can throw exceptions,
	            // but some pages display default values with javascript
	            // that are not easy to detect.
	            // trigger javascript, and ensure the box is not empty
	            // before clearing
	            try {
	                logger.debug("clearing text");
	                if (this.isDisplayed()) {
	                    String previousValue = this.getAttribute("value");
	                    if (previousValue != null && !previousValue.isEmpty()) {
	                        this.sendKeys("clearing");
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.sendKeys(Keys.BACK_SPACE);
	                        this.clear();
	                    }
	                }

	                logger.debug("cleared");
	            } catch (RuntimeException rte) {
	                logger.warn("Exception clearing text box:" + rte.getLocalizedMessage());
	            }
	            logger.debug("Entering " + this + "=" + textToEnter);
	            this.sendKeys(textToEnter);
	        } catch (WebDriverException wde) {
	            if (wde.getLocalizedMessage().contains("Element is no longer valid")) {
	                try {
	                    //DebugUIElement.debugObject(this);
	                    this.waitToBeDisplayed();
	                    this.clear();
	                    logger.debug("Entering " + this + "=" + textToEnter);
	                    this.sendKeys(textToEnter);
	                } catch (Exception e) {
	                    Assert.fail("Exception trying to send keys to " + this + " again: " + e
	                            .getLocalizedMessage());
	                }
	            }
	        } catch (Exception e) {
	            Assert.fail("Exception trying to send keys to " + this + ": " + e.getLocalizedMessage());
	        }
	        // debugObject("textInput",this);
	        textToEnter = textToEnter.replaceAll("\n", "");
	        if (this.isAbsent()) {
	            Assert.fail("could not find textBox " + this);
	        }
	        String actual = this.getAttribute("value");
	        actual = actual.replaceAll("\n", "");
	        waitToBeDisplayed();
	        Assert.assertEquals(actual.trim(), textToEnter.trim());
	    }

	    
	    public Point getLocation() {
	        Point p = null;
	        waitToBePresent();
	        p = element.getLocation();
	        logger.debug("X = " + p.getX() + "Y = " + p.getY() + " for element: " + this.toString());
	        return p;
	    }

	    
	    public Dimension getSize() {
	        Dimension size = null;
	        waitToBePresent();
	        size = element.getSize();
	        logger.debug("Height = " + size.getHeight() + ", Width = " + size.getWidth() + " for element: " + this
	                .toString());
	        return size;
	    }

	    
	    public String getCssValue(String propertyName) {
	        logger.debug("Getting value for property: " + propertyName);
	        String cssValue = defaultStr;
	        waitToBePresent();
	        cssValue = element.getCssValue(propertyName);
	        logger.debug("Got value of element " + this.toString() + " for property: " + propertyName + " with css value: " + cssValue);
	        return cssValue;
	    }

	    
	    public String getDescription() {
	        return description;
	    }

	    
	    public String toString() {
	        String desc = getDescription();
	        String elementLocator;
	        if (locator.toString().equals("") && element != null) {
	            elementLocator = "WebElement = " + element.toString();
	        } else {
	            if (!locator.toString().equals("")) {
	                elementLocator = "Locator = " + locator.toString();
	            } else {
	                elementLocator = "";
	            }
	        }
	        if (desc != null && elementLocator != null) {
	            return "[Description = {" + desc + "}, " + elementLocator + "]";
	        } else
	            if (elementLocator != null) {
	                return "[" + elementLocator + "]";
	            } else
	                if (desc != null) {
	                    return "[Description = {" + desc + "}]";
	                } else {
	                    return "";
	                }
	    }

	    
	    public void setDescription(String desc) {
	        description = desc;
	    }

	    
	    public void clear() {
	        waitToBeDisplayed();
	        logger.debug("Clear text from " + this.toString());
	        element.clear();
	    }

	    
	    public String getTagName() {
	        String tagName = defaultStr;
	        waitToBePresent();
	        tagName = element.getTagName();
	        logger.debug("Tag Name = " + tagName + ", for " + this.toString());
	        return tagName;
	    }

	    
	    public String getAttribute(String name) {
	        String attribute = defaultStr;
	        waitToBePresent();
	        attribute = element.getAttribute(name);
	        logger.debug("Attribute Name = '" + name + "' Value = " + attribute + ", for " + this
	                .toString());
	        return attribute;
	    }

	    
	    public boolean isSelected() {
	        boolean selected = false;
	        doFind();
	        if (element != null) {
	            try {
	                selected = element.isSelected();
	            } catch (StaleElementReferenceException ex) {
	                element = null;
	                selected = false;
	                logger.warn(ex);
	            }
	        }
	        logger.debug((selected ? "Element is selected " : "Element is not selected ") + this
	                .toString());
	        return selected;
	    }

	    
	    public boolean isEnabled() {
	        boolean enabled = false;
	        doFind();
	        if (element != null) {
	            try {
	                enabled = element.isEnabled();
	                logger.debug((enabled ? "Element is enabled " : "Element is disabled ") + this
	                        .toString());
	            } catch (StaleElementReferenceException ex) {
	                element = null;
	                enabled = false;
	                logger.error(ex);
	            }
	        }

	        return enabled;
	    }

	    
	    public String getText() {
	        String text = defaultStr;
	        waitToBePresent();
	        try {
	            text = element.getText();
	            logger.debug("Inner Text = " + text + ", for " + this.toString());
	        } catch (StaleElementReferenceException ex) {
	            logger.error(ex);
	        }

	        return text;
	    }

	    
	    public void waitForValueToDisplay() {
	        waitForValueToDisplay(config_wait_timeout);
	    }

	    
	    public void waitForValueToDisplay(long timeout) {
	        long startTime = System.currentTimeMillis();
	        String value = defaultStr;

	        for (long stopTime = startTime + timeout; System.currentTimeMillis() < stopTime; sleep(timeout < config_verify_interval
	                                                                                                                               ? timeout
	                                                                                                                               : config_verify_interval)) {
	            value = getValue();

	            if (!value.isEmpty()) {
	                logger.debug("Element " + this.toString() + " has value: " + value);
	                return;
	            }
	        }

	        try {
				throw new Exception(
				        "After " + (System.currentTimeMillis() - startTime) + " ms, no value is displayed for element " + this
				                .toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	    public void waitForTextToDisplay() {
	        waitForTextToDisplay(config_wait_timeout);
	    }

	    
	    public void waitForTextToDisplay(long timeout) {
	        long startTime = System.currentTimeMillis();

	        String text = defaultStr;
	        for (long stopTime = startTime + timeout; System.currentTimeMillis() < stopTime; sleep(timeout < config_verify_interval
	                                                                                                                               ? timeout
	                                                                                                                               : config_verify_interval)) {
	            text = getText();

	            if (!text.isEmpty()) {
	                logger.debug("Element " + this.toString() + " has inner text: " + text);
	                return;
	            }
	        }

	        try {
				throw new Exception(
				        "After " + (System.currentTimeMillis() - startTime) + " ms, no text is displayed for element " + this
				                .toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	    public String getTextWhenDisplayed() {
	        return getTextWhenDisplayed(config_wait_timeout);
	    }

	    
	    public String getTextWhenDisplayed(long timeout) {
	        long startTime = System.currentTimeMillis();
	        String text = defaultStr;

	        for (long stopTime = startTime + timeout; System.currentTimeMillis() < stopTime; sleep(timeout < config_verify_interval
	                                                                                                                               ? timeout
	                                                                                                                               : config_verify_interval)) {
	            text = getText();

	            if (!text.isEmpty()) {
	                logger.debug("Element " + this.toString() + " has inner text: " + text);
	                return text;
	            }
	        }

	        logger.info("After " + (System.currentTimeMillis() - startTime) + " ms, no text is displayed for element " + this
	                .toString());
	        return text;
	    }

	    
	    public boolean isDisplayed() {
	        boolean isDisplayed = false;
	        initialize();
	        if (element != null) {
	            try {
	                isDisplayed = element.isDisplayed();
	                logger.debug((isDisplayed ? "Element is displayed " : "Element is hidden ") + this
	                        .toString());
	            } catch (StaleElementReferenceException ex) {
	                element = null;
	                isDisplayed = false;
	                logger.error("StaleElementReferenceException for isDisplayed()  " + this.toString());
	            }
	        } else {
	            logger.debug(("Element is null ") + this.toString());
	        }
	        return isDisplayed;
	    }

	    
	    public boolean isHidden() {
	        boolean isHidden = false;
	        initialize();
	        if (element != null) {
	            try {
	                isHidden = !element.isDisplayed();
	                logger.debug((isHidden ? "Element is hidden " : "Element is displayed ") + this
	                        .toString());
	            } catch (StaleElementReferenceException ex) {
	                element = null;
	                isHidden = false;
	                logger.error("StaleElementReferenceException for isDisplayed()  " + this.toString());
	            }
	        } else {
	            logger.debug(("Element is null ") + this.toString());
	        }
	        return isHidden;
	    }

	    
	    public void clearAndSendKeys(CharSequence... keysToSend) {
	        waitToBePresent();
	        try {
	            this.clear();
	        } catch (WebDriverException wde) {
	            logger.warn(wde);
	        }
	        if (this.isDisplayed()) {
	            this.sendKeys(keysToSend);
	        } else {
	            logger.warn("Failed to send keys to " + this.toString());
	           // DebugUIElement.debugObject("invisible text box input", this);
	        }

	    }

	    
	    public void clickThenWaitForPageTitleChange() {
	        clickThenWaitForPageTitleChange(DUMMY_ERROR_DETECTOR);

	    }

	    
	    public void clickThenWaitForPageTitleChange(long timeout) {
	        clickThenWaitForPageTitleChange(DUMMY_ERROR_DETECTOR, timeout);

	    }

	    
	    public void clickThenWaitForPageTitleChange(ErrorDetector errorDetector) {
	        clickThenWaitForPageTitleChange(errorDetector, config_wait_timeout);
	    }

	    
	    public void clickThenWaitForPageTitleChange(ErrorDetector errorDetector, long timeout) {
	        final String previousTitle = super.getUIDriver().getTitle();
	        waitToBeDisplayed();
	        this.click();
	        ExpectedCondition<Boolean> nextPage = new ExpectedCondition<Boolean>() {

	            public Boolean apply(WebDriver d) {
	                String newTitle = d.getTitle();
	                boolean titleChanged = !newTitle.equals(previousTitle);
	                return Boolean.valueOf(titleChanged);
	            }
	        };
	        WebDriverWait wait = new WebDriverWait(super.getUIDriver(), TimeUnit.SECONDS.convert(
	                timeout,
	                TimeUnit.MILLISECONDS));
	        try {
	            wait.until(nextPage);
	            logger.debug("Clicking on " + this.toString() + " changed the page title from '" + previousTitle + "' to '" + super
	                    .getUIDriver().getTitle() + "'");
	        } catch (RuntimeException re) {
	            errorDetector.assertNoError();
	            try {
					throw new Exception(
					        "Page title did not change from " + previousTitle + " after " + timeout + " " + TimeUnit.MILLISECONDS
					                .toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void clickThenWaitForAWindowToClose() {
	        clickThenWaitForAWindowToClose(config_wait_timeout);
	    }

	    
	    public void clickThenWaitForAWindowToClose(long timeout) {
	        waitToBeDisplayed();
	        int startingNumWindowHandles = super.getUIDriver().getWindowHandles().size();
	        int currentNumWindowHandles = startingNumWindowHandles;
	        this.click();
	        logger.debug("Closing window, Number of original window handles = " + startingNumWindowHandles);
	        long stopTime = System.currentTimeMillis() + timeout;
	        do {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	            super.getUIDriver().getUIAlert().acceptAlertIfPresent(1000L);
	            currentNumWindowHandles = super.getUIDriver().getWindowHandles().size();
	            if (currentNumWindowHandles < startingNumWindowHandles) {
	                return;
	            }
	        } while (stopTime > System.currentTimeMillis());

	        try {
				throw new Exception(
				        "Timed out waiting " + timeout + " seconds for a window to close after clicking ='" + this
				                .toString() + "'");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    
	    public void clickThenWaitForAWindowToOpen() {
	        clickThenWaitForWindowToOpen(config_wait_timeout);
	    }

	    
	    public void clickThenWaitForWindowToOpen(long timeout) {
	        waitToBeDisplayed();
	        int startingNumWindowHandles = super.getUIDriver().getWindowHandles().size();
	        int currentNumWindowHandles = startingNumWindowHandles;
	        logger.debug("Opening new window, Number of original window handles = " + startingNumWindowHandles);
	        this.click();
	        long stopTime = System.currentTimeMillis() + timeout;
	        do {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	            currentNumWindowHandles = super.getUIDriver().getWindowHandles().size();
	            if (currentNumWindowHandles > startingNumWindowHandles) {
	                return;
	            }
	        } while (stopTime > System.currentTimeMillis());

	        try {
				throw new Exception(
				        "Timed out waiting " + timeout + " seconds for a window to open after clicking ='" + this
				                .toString() + "'");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    
	    public boolean containsText(String text) {
	        boolean result = element.getText().contains(text);
	        logger.debug(((result) ? "Element contains text " : "Element does not contain text ") + text + " , " + this
	                .toString());
	        return result;
	    }

	    
	    public int getHeight() {
	        int result = -1;
	        waitToBePresent();
	        result = element.getSize().getHeight();
	        logger.debug("Element height  = " + result + "px for element " + this.toString());
	        return result;

	    }

	    
	    public int getWidth() {
	        int result = -1;
	        waitToBePresent();
	        result = element.getSize().getWidth();
	        logger.debug("Element width  = " + result + "px for element " + this.toString());
	        return result;
	    }

	    
	    public void scrollIntoView() {
	        // make sure element is present to avoid javascript error
	        if (isPresent()) {
	            logger.debug("Scrolling to the element " + this.toString());
	            super.uiDriver.executeScript("arguments[0].scrollIntoView(true);", element);
	        }
	    }

	    
	    public void clickAndWait(long timeout) {
	        // waitToPresent() called by click();
	        this.click();
	        sleep(timeout, "Waiting after performed click");

	    }

	    
	    public void clickUntilAbsent(long timeout) {
	        waitToBePresent();
	        long timedout = System.currentTimeMillis() + timeout;
	        logger.debug("Waiting to disappear from DOM " + this.toString());
	        do {
	            this.click();
	            if (timeout < config_verify_interval) {
	                sleep(timeout, "Waiting for element to disappear from DOM " + this.toString());
	            } else {
	                sleep(config_verify_interval, "Waiting for element to disappear from DOM " + this
	                        .toString());
	            }
	            doFind();
	        } while (element != null && timedout > System.currentTimeMillis());
	        if (element != null) {
	            String message = this.toString() + " is still present after clicking for " + timeout + " milliseconds";
	            logger.error(message);
	            try {
					throw new Exception(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void clickUntilElementDisplayed(UIElement expected, long timeout, long intervals) {
	        clickUntilElementDisplayed(expected, timeout, intervals, new NewErrorDetector());
	    }

	    
	    public void clickUntilElementDisplayed(
	            UIElement expected,
	            long timeout,
	            long intervals,
	            ErrorDetector errorDetector) {
	        // waitToBePresent(); There is already a wait in click();
	        logger.debug("Clicking on " + this + " every " + intervals + " milliseconds until " + expected + " is visible or timeout after " + timeout);
	        long timedout = System.currentTimeMillis() + timeout;
	        do {
	            this.click();
	            sleep(intervals, "Waiting for element to be displayed " + expected);
	            errorDetector.assertNoError();
	            expected.initialize();
	        } while (!expected.isDisplayed() && timedout > System.currentTimeMillis());
	        if (!expected.isDisplayed()) {
	            String message = expected + " is is not visible after clicking for " + timeout + " milliseconds";
	            logger.error(message);
	         
	            try {
					throw new Exception(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void clickUntilElementHidden(UIElement expected, long timeout, long intervals) {
	        waitToBeDisplayed();
	        logger.debug("Clicking on " + this + " every " + intervals + " milliseconds until " + expected + " is hidden or timeout after " + timeout);
	        long timedout = System.currentTimeMillis() + timeout;
	        do {
	            this.click();
	            sleep(intervals, "Waiting for element to be hidden " + expected);
	            expected.initialize();
	        } while (expected.isDisplayed() && timedout > System.currentTimeMillis());
	        if (expected.isDisplayed()) {
	            String message = expected + " is still present after clicking " + this.toString() + " for " + timeout + " milliseconds";
	            logger.error(message);
	            try {
					throw new Exception(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	    }

	    
	    public UIActions getUIActions() {
	        this.initialize();
	        return new DefaultUIActions(uiDriver, this);
	    }

	    
	    public void setCheckbox(boolean isChecked) {
	        waitToBeDisplayed();
	        if (!isEnabled())
	            logger.info("Checkbox " + this.toString() + " is not enabled");
	        else {
	            if (isSelected() != isChecked) {
	                this.click();
	                logger.info("Set checkbox " + this.toString() + " to " + isChecked);
	                if (isSelected() != isChecked) {
	                    // sometimes IE fails to click the checkbox above, try again using javascript
	                    super.uiDriver.executeScript("arguments[0].click();", element);
	                    logger.info("2nd attempt to set checkbox " + this.toString() + " to " + isChecked);
	                }
	            }
	        }
	    }

	    
	    public String getValue() {
	        String value = defaultStr;
	        waitToBePresent();
	        value = this.getAttribute("value");
	        logger.debug("Attribute = value, Value = " + value + ", for " + this.toString());
	        return value;
	    }

	    
	    public void sendCtrlPlus(String optKey) {
	        waitToBeDisplayed();
	        this.sendKeys(Keys.CONTROL + optKey);
	    }

	    
	    public void sendEnter() {
	        waitToBeDisplayed();
	        this.sendKeys(Keys.ENTER);
	    }

	    
	    public void sendEscape() {
	        waitToBeDisplayed();
	        this.sendKeys(Keys.ESCAPE);
	    }

	    
	    public void selectByVisibleText(String text) {
	        waitToBeDisplayed();
	        Select select = new Select(element);
	        if (select.getAllSelectedOptions().size() <= 0) {
	            logger.debug("No options for selecting by text: '" + text + "', element: " + this
	                    .toString());
	        }
	        logger.debug("Selecting option with text '" + text + "' for " + this.toString());
	        select.selectByVisibleText(text);
	    }

	    
	    public void selectAllOptions() {
	        waitToBeDisplayed();
	        Select select = new Select(element);
	        for (WebElement ele : select.getOptions()) {
	            logger.debug("Selecting option with text '" + ele.getText() + "' for " + this
	                    .toString());
	            select.selectByVisibleText(ele.getText());
	        }
	    }

	    
	    public void deselectByVisibleText(String text) {
	        waitToBeDisplayed();
	        Select select = new Select(element);
	        logger.debug("Deselecting option with text " + text + " for " + this.toString());
	        select.deselectByVisibleText(text);
	    }

	    
	    public void deselectAllOptions() {
	        waitToBeDisplayed();
	        Select select = new Select(element);
	        logger.debug("Deselecting all options for " + this.toString());
	        select.deselectAll();
	    }

	    
	    public List<String> getSelectedOptions() {
	        List<String> options = new ArrayList<String>();
	        waitToBeDisplayed();
	        Select select = new Select(element);
	        logger.debug("Getting list of selected items under " + this.toString());
	        for (WebElement ele : select.getAllSelectedOptions()) {
	            logger.debug("Element is selected " + ele.toString());
	            options.add(ele.getText());
	        }
	        return options;
	    }

	    
	    public void waitForChildrenToDisplay() {
	        waitForChildrenToDisplay(config_wait_timeout);
	    }

	    
	    public void waitForChildrenToDisplay(long timeout) {
	        waitForChildrenToDisplay(DUMMY_ERROR_DETECTOR, timeout);
	    }

	    
	    public void waitForChildrenToDisplay(ErrorDetector errorDetector, long timeout) {
	        logger.debug("Wait children to appear for at most " + timeout + " milliseconds, under " + this
	                .toString());
	        long stopTime = System.currentTimeMillis() + timeout;
	        while (System.currentTimeMillis() < stopTime) {
	            errorDetector.assertNoError();
	            List<WebElement> elements = findElements(ByUtil.getBy(UIType.Xpath, "./*"));
	            if (elements != null && elements.size() > 0) {
	                for (WebElement element : elements) {
	                    if (element != null && element.isDisplayed()) {
	                        return;
	                    }
	                    if (timeout < config_verify_interval) {
	                        sleep(timeout);
	                    } else {
	                        sleep(config_verify_interval);
	                    }
	                }
	            }
	        }
	        errorDetector.assertNoError();
	        List<WebElement> elements = findElements(ByUtil.getBy(UIType.Xpath, "./*"));
	        if (elements != null && elements.size() > 0) {
	            for (WebElement element : elements) {
	                if (element != null && element.isDisplayed())
	                    return;
	            }
	        }
	        try {
				throw new Exception(
				        "Failed while waiting for elements '" + this.toString() + "' children to be displayed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	    public void waitToBePresent(ErrorDetector errorDetector, long timeout) {
	        long startTime = System.currentTimeMillis();
	        long stopTime = startTime + timeout;
	        logger.debug("Waiting " + timeout + "ms for element to be present " + this.toString());
	        while (!doFind() && System.currentTimeMillis() < stopTime) {
	            errorDetector.assertNoError();
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        if (doFind()) {
	            return;
	        } else {
	            String errorMessage = "After " + (System.currentTimeMillis() - startTime) + "ms, failed while element initialization " + this
	                    .toString();
	            logger.error(errorMessage);
	            try {
					throw new Exception(errorMessage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBePresent() {
	        waitToBePresent(config_verify_timeout);
	    }

	    
	    public void waitToBePresent(long timeout) {
	        long startTime = System.currentTimeMillis();
	        long stopTime = startTime + timeout;
	        logger.debug("Waiting " + timeout + "ms for element to be present " + this.toString());
	        while (!doFind() && System.currentTimeMillis() < stopTime) {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        if (doFind()) {
	            return;
	        } else {
	            String errorMessage = "After " + (System.currentTimeMillis() - startTime) + "ms, failed while element initialization " + this
	                    .toString();
	            logger.error(errorMessage);
	            try {
					throw new Exception(errorMessage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public boolean isDisplayedAfterWaiting() {
	        return isDisplayedAfterWaiting(config_wait_timeout);
	    }

	    
	    public boolean isDisplayedAfterWaiting(long timeout) {
	        try {
	            waitToBeDisplayed(timeout);
	            logger.debug("Element is displayed " + this.toString());
	            return true;
	        } catch (Exception toe) {
	            logger.debug("Element is NOT displayed " + this.toString());
	            return false;
	        }
	    }

	    
	    public void waitToBeDisplayed() {
	        waitToBeDisplayed(config_wait_timeout);
	    }

	    
	    public void waitToBeDisplayed(long timeout) {
	        long startTime = System.currentTimeMillis();
	        long stopTime = startTime + timeout;
	        boolean isDisplayed = false;
	        logger.debug("Waiting " + timeout + "ms for element to be displayed " + this.toString());
	        while (System.currentTimeMillis() < stopTime) {
	            if (isDisplayed = isDisplayed())
	                break;
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	            if (uiDriver.getWebDriver() instanceof InternetExplorerDriver) {
	                // this method was failing sometimes in IE, the following line fixed the problem
	                scrollIntoView();
	            }
	        }
	        isDisplayed = isDisplayed ? isDisplayed : isDisplayed();
	        long waitTime = System.currentTimeMillis() - startTime;
	        if (element != null && isDisplayed) {
	            logger.debug("After " + waitTime + "ms, element is displayed " + this.toString());
	            return;
	        } else {
	            String errorMessage = "After " + waitTime + "ms, failed to display element " + this
	                    .toString();
	            logger.error(errorMessage);
	            try {
					throw new Exception(errorMessage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBeHidden() {
	        waitToBeHidden(config_wait_timeout);
	    }

	    
	    public void waitToBeHidden(ErrorDetector errorDetector, long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        boolean isDisplayed = true;
	        logger.debug("Waiting for element to be hidden " + this.toString());
	        while (System.currentTimeMillis() < stopTime) {
	            errorDetector.assertNoError();
	            if (!(isDisplayed = isDisplayed()))
	                break;
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        isDisplayed = isDisplayed();
	        if (!isDisplayed) {
	            return;
	        } else {
	            try {
					throw new Exception("Failed to hide element " + this.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBeHidden(long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        boolean isDisplayed = true;
	        logger.debug("Waiting for element to be hidden " + this.toString());
	        while (System.currentTimeMillis() < stopTime) {
	            if (!(isDisplayed = isDisplayed()))
	                break;
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        isDisplayed = isDisplayed();
	        if (!isDisplayed) {
	            return;
	        } else {
	            try {
					throw new Exception("Failed to hide element " + this.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBeAbsent() {
	        waitToBeAbsent(config_wait_timeout);
	    }

	    
	    public void waitToBeAbsent(long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        logger.debug("Waiting for element to be absent in DOM " + this.toString());
	        while (doFind() && System.currentTimeMillis() < stopTime) {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        doFind();
	        if (element == null) {
	            return;
	        } else {
	            try {
					throw new Exception("Element still present on the page " + this.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBeEnabled() {
	        waitToBeEnabled(config_wait_timeout);
	    }

	    
	    public void waitToBeEnabled(long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        waitToBePresent(timeout);
	        logger.debug("Waiting for element to be enabled " + this.toString());
	        while ((!isEnabled()) && System.currentTimeMillis() < stopTime) {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        if (element != null && isEnabled()) {
	            return;
	        } else {
	            try {
					throw new Exception("Failed to enable " + this.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitToBeDisabled() {
	        waitToBeDisabled(config_wait_timeout);
	    }

	    
	    public void waitToBeDisabled(long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        logger.debug("Waiting for element to be disabled " + this.toString());
	        while (doFind() && isEnabled() && System.currentTimeMillis() < stopTime) {
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        if (element == null || !isEnabled()) {
	            return;
	        } else {
	            try {
					throw new Exception("Failed to disable " + this.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitForAttributeToContain(String attribute, String partialValue) {
	        waitForAttributeToContain(attribute, partialValue, config_wait_timeout);
	    }

	    
	    public void waitForAttributeToContain(String attribute, String partialValue, long timeout) {
	        long stopTime = System.currentTimeMillis() + timeout;
	        while (System.currentTimeMillis() < stopTime) {
	            String attr = getAttribute(attribute);
	            if (attr != null && attr.contains(partialValue)) {
	                logger.debug(this.toString() + " has attribute '" + attribute + "' that contains value '" + partialValue + "'");
	                return;
	            } else {
	                throw new TimeoutException(
	                        "Element " + this.toString() + " does not contains partial value '" + partialValue + "' for attribute '" + attribute + "'. value = " + attr);
	            }
	        }
	    }

	    
	    public boolean doFind() {
	        this.initialize();
	        return this.isInitialized();
	    }

	    
	    public void assertInputContains(String expected) {
	        initialize();
	        logger.debug("Validate that  '" + expected + "' is already in the " + this.toString() + " box");
	        String actual = element.getText();
	        Assert.assertTrue(actual.contains(expected));
	    }

	    
	    public void assertNotDisplayed(long timeout) {
	        this.initialize();
	        Assert.assertNotNull(element);
	        Assert.assertFalse(this.isDisplayed());
	    }

	    
	    public Coordinates getCoordinates() {
	        return ((Locatable) element).getCoordinates();
	    }

	    public void clickUntilElementsDisplayed(List<UIElement> expected, long timeout, long intervals) {
	        clickUntilElementsDisplayed(expected, timeout, intervals, new NewErrorDetector());
	    }

	    public void clickUntilElementsDisplayed(
	            List<UIElement> expected,
	            long timeout,
	            long intervals,
	            ErrorDetector errorDetector) {
	        // waitToBePresent(); //There is already a wait in click();
	        boolean isAnyElemDisplayed = false;
	        logger.debug("Clicking on " + expected + " every " + intervals + " milliseconds until visible or timeout after " + timeout);
	        long timedout = System.currentTimeMillis() + timeout;
	        do {
	            this.click();
	            sleep(intervals);
	            errorDetector.assertNoError();
	            for (UIElement elem : expected) {
	                elem.initialize();
	                if (elem.isDisplayed()) {
	                    isAnyElemDisplayed = true;
	                    break;
	                }
	            }

	        } while (!isAnyElemDisplayed && timedout > System.currentTimeMillis());
	        if (!isAnyElemDisplayed) {
	            String message = "none of the expected elements are not visible after clicking for " + timeout + " milliseconds. expected elements: " + expected;
	            logger.error(message);
	           
	            try {
					throw new Exception(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	    
	    public void waitForMatchText(String text, SearchScope scope) {
	        long timeout = System.currentTimeMillis() + config_wait_timeout;
	        String currentText = "";
	        while (System.currentTimeMillis() < timeout) {
	            currentText = getText();
	            if (currentText != null)
	                switch (scope) {
	                    case CONTAINS:
	                        if (currentText.contains(text))
	                            return;
	                    case EQUALS:
	                        if (currentText.equals(text))
	                            return;
	                }
	            if (timeout < config_verify_interval) {
	                sleep(timeout);
	            } else {
	                sleep(config_verify_interval);
	            }
	        }
	        currentText = getText();
	        if (currentText != null)
	            switch (scope) {
	                case CONTAINS:
	                    if (currentText.contains(text))
	                        return;
	                case EQUALS:
	                    if (currentText.equals(text))
	                        return;
	            }
	        try {
				throw new Exception(
				        "The element did not display text '" + text + "' as expected. instead it displayed '" + currentText + "'.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	    public boolean hasText() {
	        return element != null && element.isDisplayed() && !element.getText().isEmpty();
	    }

	    /* (non-Javadoc)
	     * @see org.openqa.selenium.TakesScreenshot#getScreenshotAs(org.openqa.selenium.OutputType)
	     */
	    
	    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
	        return element.getScreenshotAs(target);
	    }

}
