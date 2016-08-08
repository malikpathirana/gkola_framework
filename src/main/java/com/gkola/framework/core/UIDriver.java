package com.gkola.framework.core;

import java.util.ArrayList;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;

import com.gkola.framework.drivers.UIAlert;
import com.gkola.framework.util.SearchScope;
import com.gkola.framework.util.UIType;

public interface UIDriver  extends WebDriver, JavascriptExecutor, HasCapabilities, Navigation {
	 /**
     * Delete all the cookies for the current domain.
     * 
     * @see Options#deleteAllCookies()
     */
    public void deleteAllCookies();

    String executeScript(String script);

    /**
     * 
     * Load URL
     * 
     * @param arg0
     */
    public void get(String arg0);

    /**
     * Get all links on the current page <br>
     * Get the value of {@code href} attribute within {@code <a>} tag
     * 
     * @return all links on current page
     */
    public ArrayList<String> getAllLinksOnPage();

    /**
     * JavaScript call {@code return [element].innerHTML; }
     * 
     * <p>
     * Returns the innerHTML of the provided UIElement as returned by the javascript method
     * [element].innerHTML, if and only if the browser's javascript engine supports innerHTML.
     * </p>
     * 
     * @param element
     *            as the UIElement
     * @return the innerHTML of the UIElement
     */
    String getInnerHtmlOf(UIElement element);

    /**
     * JavaScript call {@code return [element].outerHTML; }
     * 
     * <p>
     * Returns the outerHTML of the provided UIElement as returned by the javascript method
     * [element].outerHTML, if and only if the browser's javascript engine supports outerHTML.
     * </p>
     * 
     * @param element
     *            as the UIElement
     * @return the outerHTML of the UIElement
     */
    String getOuterHtmlOf(UIElement element);

    /**
     * JavaScript call {@code return document.getElementById(id).getAtribute(attribute)}
     * 
     * @param id
     * @param attribute
     * @return
     */
    public String getAttributeFromDisabledFieldByID(String id, String attribute);

    /*
     * TODO Implement in concrete class
     * 
     * public String getBrowserType();
     */

    /**
     * Uses JavaScript call {@code return document.getElementById(id).value}
     * 
     * @param id
     * @return
     */
    public String getValueFromDisabledFieldByID(String id);

    /**
     * Check if driver is available
     * 
     * isAvailable returns true when the browser is open
     * isAvailable returns false when the browser is closed.
     * isAvailable NEVER throws exceptions.
     * 
     * @return
     */
    public boolean isAvailable();

    /*
     * TODO Implement in concrete class Checks if the server reset the connection with the browser
     * 
     * public boolean isConnectionReset();
     */

    /**
     * 
     * @param substring
     *            - substring to look for in page title
     * @return {@code true} if page title contains {@code substring}, {@code false} otherwise
     */
    public boolean isWindowTitleContains(String substring);

    Dimension getWindowSize();

    /**
     * @return the client's screen size
     */
    Dimension getClientScreenSize();

    /**
     * Maximize current window to full screen
     */
    void maximizeWindow();

    /*
     * TODO Implement in concrete class
     * 
     * public void reloadPage();
     */

    /*
     * TODO Implement in concrete class <br> Reloads the page if connection was reset
     * 
     * public void retryIfReset();
     */

    /**
     * JavaScript call to scroll to the bottom of the page
     */
    void scrollToPageBottom();

    /*
     * TODO Implement in concrete class
     * 
     * public WebElement waitForElementBy(By how, boolean failIfMissing, int timeoutSeconds);
     */

    /*
     * TODO Implement in concrete class <br>
     * 
     * Duplicate with waitForElementBy, we just need to preserve failIfMissing flag Wait for an
     * element that has the id and then return it.
     * 
     * @param driver web driver pointing to current page
     * 
     * @param text the element must contain
     * 
     * @return the element containing the text
     * 
     * public WebElement waitForID(String ID, boolean failIfMissing, int timeoutSeconds);
     */

    /**
     * 
     * @param textArray
     * @return array of strings that are present on page
     * 
     */
    public String[] waitForTextOnPage(String[] expectedArray);

    /*
     * TODO Implement in concrete class
     * 
     * 
     * public void waitForPageToContainText(String text, int timeoutSeconds, ErrorDetector parser);
     */

    /**
     * Wait for text to be absent in DOM
     * 
     * @param expected
     */
    void waitToBeAbsent(UIElement expected);

    /**
     * Wait for text to be absent in DOM
     * 
     * @param expected
     * @param timeout
     */
    void waitToBeAbsent(UIElement expected, long timeout);

    /**
     * Wait for {@code expected} element to be present in the DOM
     * 
     * @param expected
     */
    void waitToBePresent(UIElement expected);

    /**
     * Wait for {@code expected} element to be present in the DOM
     * 
     * @param expected
     * @param timeout
     */
    void waitToBePresent(UIElement expected, long timeout);

    /**
     * Wait for {@code stalling} element to be appear for {@code timeToAppear} and if it appears,
     * wait {@code timeToDisappear} to be hidden from the page
     * 
     * @param stallingElement
     * @param timeToAppear
     * @param timeToBeHidden
     */
    void waitForStallingElementToBeHidden(
            UIElement stallingElement,
            long timeToAppear,
            long timeToBeHidden);

    UIAlert getUIAlert();

    /**
     * @return static driver instance
     */
    WebDriver getWebDriver();
  
    /**
     * Is the specified text visible anywhere on the page regardless of which element contains it?
     * 
     * @param text
     *            to find
     * @return true if an element on the page contains this text
     */
    boolean isTextVisible(String text);

    /**
     * 
     * @return
     */
    // @Deprecated
    String getUIObjectType();

    /**
     * Test if browser has closed by trying to count the windows. This will throw a
     * SessionNotFoundException if the browser is closed;
     * hasQuit() is the opposite of isAvailable().
     * neither will ever throw an exception.
     * 
     * @return true if the browser has closed.
     */
    boolean hasQuit();

    /**
     * Wait for text to display {@code text}
     * 
     * @param text
     * @deprecated use {@link #waitForTextToDisplay2(String)}
     */
    void waitForTextToDisplay(String text);

    /**
     * Wait for text to display {@code text}
     * 
     * @param text
     * @param timeout
     * @deprecated use {@link #waitForTextToDisplay2(String, long)}
     */
    void waitForTextToDisplay(String text, long timeout);

    /**
     * Wait for text to be hidden
     * 
     * @param text
     * @deprecated use {@link #waitForTextToHide2(String)}
     */
    void waitForTextToHide(String text);

    /**
     * Wait for text to be hidden
     * 
     * @param text
     * @param timeout
     * @deprecated use {@link #waitForTextToHide2(String, long)}
     */
    void waitForTextToHide(String text, long timeout);

    /**
     * Wait for {@code expected} element to be displayed on the page
     * 
     * @param expected
     */
    void waitToBeDisplayed(UIElement expected);

    /**
     * Wait for {@code expected} element to be displayed on the page
     * 
     * @param expected
     * @param timeout
     */
    void waitToBeDisplayed(UIElement expected, long timeout);

    /**
     * Wait for {@code expected} element to be hidden
     * 
     * @param expected
     */
    void waitToBeHidden(UIElement expected);

    /**
     * Wait for {@code expected} element to be hidden
     * 
     * @param expected
     * @param timeout
     */
    void waitToBeHidden(UIElement expected, long timeout);

    /**
     * Get window locator
     * 
     * @return
     */
   // UIWindowLocator getUIWindowLocator();

    /**
     * @param expectedArray
     * @return
     */
    String[] whichTextIsOnPage(String[] expectedArray);

    /**
     * 
     * @return mechanism to switch to frames and iframes
     */
   // UIFrameLocator getUIFrameLocator();

    /**
     * Wait until {@code <title>} matches {@code title}
     * 
     * @param title
     * @param scope
     */
    void waitForTitleToMatch(String title, SearchScope scope);

    /**
     * Check if {@code text} is present in {@code <title>}
     * 
     * @param text
     * @return true if text is substring of title, false otherwise
     */
    boolean isTextPresentInTitle(String text);

    /**
     * Get xpath for any element on the page
     * 
     * @param element
     * @return
     */
    String getXPath(WebElement element);

    /**
     * Wait for title to change
     * 
     * @param previousTitle
     */
    void waitForTitleToChange(String previousTitle);

    /**
     * Wait for title to change
     * 
     * @param previousTitle
     */
    void waitForTitleToChange(String previousTitle, long timeout);

    /**
     * Position the browser using javascript. Useful for local testing or ensuring that the size is
     * large enough to display the page
     * 
     * @param x
     *            left side of browser from left side of screen
     * @param y
     *            top of browser from top of screen
     * @param height
     *            height of browser
     * @param width
     *            Width of browser
     */
    void moveBrowserToXYHeightWidth(int x, int y, int height, int width);

    /**
     * Determine if a capability is listed as available by the current webdriver
     * 
     * @param capability
     * @return true if the String appears in the list of capabilities
     */
    boolean hasCapability(String capability);

    /**
     * @param type
     * @param value
     * @return
     */
    UIElement findUIElement(UIType type, String value);

    /**
     * @param type
     * @param value
     * @param description
     * @return
     */
    UIElement findUIElement(UIType type, String value, String description);

    /**
     * @param levelGreaterThan
     */
    LogEntries getConsoleLog(Level levelFilter);

    /**
     * @Deprecated Use UIElement.sendKeys(keys) for specific element.<br/>
     *             Send a CharSequence (such as from the {@link Keys} enum) to the browser. This is
     *             useful for
     *             sending Keys.TAB to switch between elements. see
     *             {@link org.openqa.selenium.interactions.Actions#sendKeys(java.lang.CharSequence...)}
     * 
     * @param keysToSend
     */
    @Deprecated
    void sendKeysToBrowser(CharSequence... keysToSend);

    /**
     * Wait for {@code text} to display
     * 
     * @param text
     */
    void waitForTextToDisplay2(String text);

    /**
     * Wait for {@code text} to display
     * 
     * @param text
     * @param timeout
     */
    void waitForTextToDisplay2(String text, long timeout);

    /**
     * Wait for {@code text} to be hidden
     * 
     * @param text
     */
    void waitForTextToHide2(String text);

    /**
     * Wait for {@code text} to be hidden
     * 
     * @param text
     * @param timeout
     */
    void waitForTextToHide2(String text, long timeout);

    /**
     * 
     * @param expectedArray
     * @param timeout
     * @return array of strings that are present on page
     */
    String[] waitForTextOnPage(String[] expectedArray, long timeout);

    /**
     * Wait until {@code <title>} matches {@code title}
     * 
     * @param title
     * @param scope
     * @param timeout
     */
    void waitForTitleToMatch(String title, SearchScope scope, long timeout);

    /**
     *
     */
    void quit();

    public boolean isPhysicalIosDevice();

	
}
