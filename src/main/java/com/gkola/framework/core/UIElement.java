package com.gkola.framework.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import com.gkola.framework.drivers.ErrorDetector;
import com.gkola.framework.elements.UIActions;
import com.gkola.framework.util.SearchScope;

public interface UIElement extends WebElement,UIEleBase,Locatable{
	/**
     * Wait for element to be hidden
     * 
     * @param waitTime
     */
    void waitToBeHidden(long waitTime);

    /**
     * Wait for element to be displayed
     * 
     * @param waitTime
     */
    void waitToBeDisplayed(long waitTime);

    /**
     * Wait for an element to be displayed, but do not fail. This method name conforms to the is vs.
     * wait naming convention.
     * 
     * @return
     */
    boolean isDisplayedAfterWaiting();

    /**
     * Wait for an element to be displayed, but do not fail. This method name conforms to the is vs.
     * wait naming convention.
     * 
     * @param waitTime
     * @return
     */
    boolean isDisplayedAfterWaiting(long waitTime);

    /**
     * TODO priority 7 <br>
     * Check for IMG tag with specified SRC attribute
     * 
     * @param src
     * @return true if matching element is found.
     */
    // public boolean checkForImageSrc(String src);

    /**
     * TODO priority 7 <br>
     * NOT duplicate, we can NOT do this using config file
     * 
     * This element may or may not show up, if it does not show up, test will continue after the
     * given timeout has expired, if it does show up then the test will wait for it to go away or
     * throw a failure if it remains past the standard timeout
     * 
     * Best used on loading elements that show up out of the blue seemingly randomly
     * 
     * @param by
     * @param timeoutForElementToAppear
     * @param timeoutForElementToDisappear
     * 
     */
    // public void checkForStallingElement(
    // By by,
    // int timeoutForElementToAppear,
    // int timeoutForElementToDisappear);

    /**
     * Clears the input and sends the text to it
     * 
     * @param keysToSend
     *            the keys to send to the element
     */
    public void clearAndSendKeys(CharSequence... keysToSend);

    /**
     * sendKeysToTextInput with rigorous error handling
     * 
     * @param textToEnter
     *            text to send to the input box
     * 
     *            Formerly public void sendKeysToTextInputBy(By byHow, String textToEnter); public
     *            void sendKeysToTextInputID(String ID, String textToEnter);
     *            This method accomplishes the same goal as clearAndSendKeys but with extensive
     *            fault
     *            tollerence, checking and retrying. Use this when the textbox has trouble clearing.
     *            Useful for when chromedriver does not allow sendKeys() to work:
     *            <link>https://code.google.com/p/chromedriver/issues/detail?id=35</link>
     * @param textToEnter
     */

    public void clearAndSendKeysRobustly(String textToEnter);

    /**
     * TODO priority 6 <br>
     * Click an element and wait for the page to change. If there is an error, then a message will
     * be displayed on the same page instead of going to the next page. This function will complete
     * after either an error message appears or the browser goes to the next page, and will return
     * true if the title changes, otherwise false.
     * 
     * Implemented with call to public void clickAndAssertPageUrlChange(ErrorDetector parser);
     * 
     * Formerly public void clickAndAssertPageUrlChange(WebElement element);
     */
    // public void clickAndAssertPageUrlChange();

    /**
     * TODO priority 6 <br>
     * Click an element and wait for the page to change. If there is an error, then a message will
     * be displayed on the same page instead of going to the next page. This function will complete
     * after either an error message appears or the browser goes to the next page, and will return
     * true if the title changes, otherwise false.
     * 
     * @param parser
     * 
     *            Formerly public void clickAndAssertPageUrlChange(WebElement element, ErrorDetector
     *            parser);
     */
    // public void clickAndAssertPageUrlChange(ErrorDetector parser);

    /**
     * Click element and timeout.
     * 
     * @param timeout
     *            - milliseconds to wait
     */
    void clickAndWait(long timeout);

    /**
     * Clicks the element and wait for a window to change title
     */
    public void clickThenWaitForPageTitleChange();

    /**
     * Clicks the element and wait for a window to change title
     * 
     * @param timeout
     *            - how much time to wait for a new page load
     */
    void clickThenWaitForPageTitleChange(long timeout);

    /**
     * Clicks the element and wait for a window to change title
     * 
     * @param errorDetector
     */
    public void clickThenWaitForPageTitleChange(ErrorDetector errorDetector);

    /**
     * Clicks the element and wait for a window to change title
     * 
     * @param timeoutSeconds
     * @param errorDetector
     */

    public void clickThenWaitForPageTitleChange(ErrorDetector errorDetector, long timeout);

    /**
     * TODO priority 6 <br>
     * A method to deal with finicky buttons that open popups. It is a little buggy and not used at
     * the moment.
     * 
     * @param driver
     * @param button
     * 
     *            Formerly public void clickButtonAndWaitForNewWindow(WebElement button);
     */
    // public void clickButtonAndWaitForNewWindow();

    /**
     * TODO priority 7 <br>
     * Press a button repeatedly until success is signaled by the appearance of text.
     * 
     * @param findText
     *            the text that signals the button was successfully pressed
     * @param detailedError
     *            an error message specific to the test calling the message
     * @param timeout
     *            how long to attempt pressing the button in seconds.
     * 
     *            Formerly public void clickButtonUntilTextShowsUp(String id, String findText, int
     *            timeout);
     */
    // public void clickButtonUntilTextShowsUp(String findText, int timeout);

    /**
     * TODO priority 8 <br>
     * Press a button repeatedly until success is signaled by the appearance of text.
     * 
     * @param findText
     *            the text that signals the button was successfully pressed
     * @param detailedError
     *            an error message specific to the test calling the message
     * @param timeout
     *            how long to attempt pressing the button in seconds.
     * 
     *            Formerly public void clickButtonUntilTextShowsUp( By how, String findText, String
     *            detailedError, int timeout);
     */
    // public void clickButtonUntilTextShowsUp(String findText, String detailedError, int timeout);

    /**
     * TODO priority 9 <br>
     * 
     * For buttons that don't activate when you click on them: 1. Click, 2. Check to see if the
     * expected element appears. 3. If not, check for an alternate element. 4. If not, click again.
     * 
     * @param buttonDescription
     *            a business level description of the button
     * @param confirmWithCss
     *            locator for first possible confirming element
     * @param alternateConfirmCss
     *            locator for second possible confirming element
     * @param detector
     * 
     *            Formerly public void clickStubbornButtonBy( By button, String buttonDescription,
     *            By confirmWithElement, By alternateConfirm, ErrorDetector parser);
     */
    // public void clickStubbornly(
    // String buttonDescription,
    // By confirmWithElement,
    // By alternateConfirm,
    // ErrorDetector detector);

    /**
     * 
     * Clicks the closing element and wait for the number of open windows to decrease, if the number
     * does not decrease throws {@link TimeOutException}
     * 
     * @throws TimeOutException
     */
    public void clickThenWaitForAWindowToClose();

    /**
     * 
     * Clicks the closing element and wait for the number of open windows to decrease, if the number
     * does not decrease throws {@link TimeOutException}
     * 
     * @param timeOut
     *            - duration to wait before timeout in ms
     * @throws TimeOutException
     */
    public void clickThenWaitForAWindowToClose(long timeout);

    /**
     * TODO priority 7 <br>
     * Will get the handle to the current window and continue once this window has closed. Use this
     * to click a button you expect to close a window but there is a delay, that otherwise
     * interacting with the window if you are not sure if it is closed or not will cause selenium
     * driver to crash.
     * 
     * Formerly public void clickThenWaitForWindowToClose(By byHow);
     */
    // public void clickThenWaitForThisWindowToClose();

    /**
     * * TODO priority 7 <br>
     * Will get the handle to the current window and continue once this window has closed. Use this
     * to click a button you expect to close a window but there is a delay, that otherwise
     * interacting with the window if you are not sure if it is closed or not will cause selenium
     * driver to crash.
     * 
     * @param timeoutSeconds
     *            Formerly public void clickThenWaitForWindowToClose(By byHow, int timeoutSeconds);
     */
    // public void clickThenWaitForThisWindowToClose(int timeoutSeconds);

    /**
     * Will check the number of active windows and continue once one has opened. Use this to click a
     * button you expect to open a window but there is a delay, and you dont want to switch to a new
     * window until you know one has opened
     */
    public void clickThenWaitForAWindowToOpen();

    /**
     * 
     * Will check the number of active windows and continue once one has opened. Use this to click a
     * button you expect to open a window but there is a delay, and you dont want to switch to a new
     * window until you know one has opened
     * 
     * @param timeOut
     *            duration to wait until timeout in ms
     */
    public void clickThenWaitForWindowToOpen(long timeout);

    /**
     * Keep on clicking element every {@code interval} until {@code expected} element is present or
     * {@code timeout} is reached.
     * 
     * @param expected
     *            Expected UIElement
     * @param timeout
     *            duration to wait in ms
     * @param intervals
     *            duration to pause between clicks in ms.
     * 
     */
    void clickUntilElementDisplayed(UIElement expected, long timeout, long intervals);

    void clickUntilElementDisplayed(
            UIElement expected,
            long timeout,
            long intervals,
            ErrorDetector errorDetector);

    /**
     * Keep on clicking element every {@code interval} seconds until {@code expected} element is not
     * visible or {@code timeout} is reached.
     * 
     * @param expected
     *            Element that you expect to appear
     * @param timeout
     *            duration to wait in ms
     * @param intervals
     *            duration to pause between clicks in ms.
     * 
     * @throws TimeOutException
     */
    void clickUntilElementHidden(UIElement expected, long timeout, long intervals);

    /**
     * Click on element until it is null (not in the DOM) , or timeout is reached
     * 
     * @param timeout
     * @throws TimeOutException
     */
    void clickUntilAbsent(long timeout);

    /**
     * Check to see if text is contained in the current element's text node.
     * 
     * @return true if text node contains matching text
     */
    boolean containsText(String text);

    /**
     * 
     * Tries to initialize {@link WebElement} on current page
     * 
     * @return true if element was initialized, false otherwise
     * 
     */
    boolean doFind();

    /**
     * Initialize {@code UIElement}
     */
    void initialize();

    /**
     * @return true if {@code UIElement} is initialized, false if {@code UIElement} is <i>NULL</i>
     */
    boolean isInitialized();

    /**
     * Finds child {@link UIElement}
     * 
     * @param by
     * @return Desired {@link UIElement} or {@code null} if {@link UIElement} was not found
     */
    UIElement findUIElement(By by);

    /**
     * Finds child {@link UIElement}
     * 
     * @param by
     * @param timeout
     * @return Desired {@link UIElement} or {@code null} if {@link UIElement} was not found
     */
    UIElement findUIElement(By by, long timeout);

    /**
     * Finds list of children {@link UIElement}s
     * 
     * @param by
     * @return List of desired {@link UIElement} or {@code null} if {@link UIElement} was not found
     */
    List<UIElement> findUIElements(By by);

    /**
     * Finds list of children {@link UIElement}s
     * 
     * @param by
     * @param timeout
     * @return List of desired {@link UIElement} or {@code null} if {@link UIElement} was not found
     */
    List<UIElement> findUIElements(By by, long timeout);

    /**
     * Uses JavaScript to discover the height of the current element
     * 
     * @return height in pixels, or -1 if failed to get height
     * 
     */
    public int getHeight();

    /**
     * Get selected options for multiple select node
     * 
     * @return selected options
     */
    List<String> getSelectedOptions();

    /**
     * Get value of {@code value} attribute
     * 
     * @return value of {@code value} attribute
     */
    String getValue();

    /**
     * 
     * Uses JavaScript to discover the width of the current element
     * 
     * @return width in pixels, or -1 if failed to get width
     */
    public int getWidth();

    /**
     * 
     * TODO priority 1 <br>
     * Check if element is visible
     * 
     * @return {@code true} if visible, {@code false} otherwise
     */
    // boolean isDisplayed(ErrorDetector errorDetector);

    /**
     * 
     * Tries to find {@link WebElement} on current page
     * 
     * @return {@code true} if element is found, {@code false} otherwise
     */
    // boolean isFound();

    /**
     * Replace value for dynamically defined element
     * 
     * @param values
     * @return
     */
    UIElement replaceValues(String... values);

    /**
     * Scroll element into view using JavaScript
     */
    public void scrollIntoView();

    /**
     * Set checked status of check box to specific value.
     * 
     * @param isChecked
     *            - will toggle check box if {@code true}, will uncheck check box if {@code false}
     * 
     */
    void setCheckbox(boolean isChecked);

    /**
     * Send control + {@code optKey} to the element
     * 
     * @param optKey
     */
    void sendCtrlPlus(String optKey);

    /**
     * Send Enter key to the element
     */
    void sendEnter();

    /**
     * Send escape key to the element
     */
    void sendEscape();

    /**
     * Verify text box contains {@code expected}
     * 
     * @param expected
     */
    public void assertInputContains(String expected);

    /**
     * Asserts that element exists on the page, but is not visible
     * 
     * @param timeout
     */
    public void assertNotDisplayed(long timeout);

    /**
     * Wait for element to be present in DOM
     */
    public void waitToBePresent();

    /**
     * Wait for element to be present in DOM
     * 
     * @param timeout
     */
    public void waitToBePresent(long timeout);

    /**
     * Wait for children elements to be displayed
     */
    void waitForChildrenToDisplay();

    /**
     * Wait for elements children to be displayed
     * 
     * @param timeout
     */
    void waitForChildrenToDisplay(long timeout);

    /**
     * Wait for elements children to be displayed
     * 
     * @param errorDetector
     * @param timeout
     */
    void waitForChildrenToDisplay(ErrorDetector errorDetector, long timeout);

    /**
     * TODO priority 1 <br>
     * Wait for this element to appear and become visible
     * 
     * @param failIfMissing
     *            do we want to throw an exception if it is missing?
     * @param timeoutSeconds
     *            how long should we wait?
     * @return true if the element became visible
     * 
     *         Formerly: public WebElement waitBy( By byHow, boolean failIfMissing, int
     *         timeoutSeconds, ErrorDetector errorDetector);
     */
    // public boolean waitForAppearVisible(
    // boolean failIfMissing,
    // int timeoutSeconds,
    // ErrorDetector errorDetector);

    /**
     * TODO priority 1 <br>
     * Waits {@code waitSeconds} seconds to find child {@link UIElement} and then throws exception
     * 
     * @param by
     * @param waitSeconds
     * @return Desired {@link UIElement} or throws exception if {@link UIElement} was not found
     * 
     *         Formerly UIElement findUIElement(By by, long waitTime); PearsonWebDriver.waitBy()
     */
    // UIElement waitForUIElement(By by, long waitSeconds);

    /**
     * TODO priority 1 <br>
     * Waits for list of children {@link UIElement}s
     * 
     * @param by
     * @return List of desired {@link UIElement} or throws exception if {@link UIElement} was not
     *         found
     */
    // List<UIElement> waitForUIElements(By by, long waitTime);

    /**
     * Wait for element to be absent in DOM
     */
    void waitToBeAbsent();

    /**
     * TODO priority 2 <br>
     * 
     * Wait for the current element to not be present. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param errorDetector
     *            class that can report error messages
     * @return true if children appear, throws exception otherwise
     * 
     */
    // boolean waitToAbsent(ErrorDetector errorDetector);

    /**
     * Wait for element to be absent in DOM
     * 
     * @param timeout
     */
    void waitToBeAbsent(long timeout);

    /**
     * TODO priority 2 <br>
     * 
     * Wait for the current element to not be present. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param waitTime
     *            how long to wait for the element to be absent
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is absent and there is no error, throws exception otherwise
     */
    // boolean waitToAbsent(long waitTime, ErrorDetector errorDetector);

    /**
     * TODO priority 2 <br>
     * 
     * Wait for the current element to be visible. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param waitTime
     *            how long to wait for this element to become visible
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is visible and there is no error, throws exception otherwise
     */
    // boolean waitToAppear(ErrorDetector errorDetector, long waitTime);

    /**
     * TODO priority 3 <br>
     * 
     * Wait for the current element to be disabled. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is disabled and there is no error, throws exception otherwise
     */
    // boolean waitToDisabled(ErrorDetector errorDetector);

    /**
     * TODO priority 3 <br>
     * 
     * Wait for the current element to be disabled. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param waitTime
     *            how long to wait for this element to become disabled
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is disabled and there is no error, throws exception otherwise
     */
    // boolean waitToDisabled(long waitTime, ErrorDetector errorDetector);

    /**
     * TODO priority 2 <br>
     * 
     * Wait for the current element to be invisible. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is invisible and there is no error, throws exception otherwise
     */
    // boolean waitToDisappear(ErrorDetector errorDetector);

    /**
     * TODO priority 2 <br>
     * 
     * Wait for the current element to be invisible. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param waitTime
     *            how long to wait for this element to become invisible
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is invisible and there is no error, throws exception otherwise
     */
    // boolean waitToDisappear(long waitTime, ErrorDetector errorDetector);

    /**
     * TODO priority 1 <br>
     * 
     * Wait for the current element to be enabled. Throw exception if the time passed exceeds
     * {@code}waitTime{@code} passes or an error is detected
     * 
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is enabled and there is no error, throws exception otherwise
     */
    // boolean waitToEnabled(ErrorDetector errorDetector);

    /**
     * TODO priority 1 <br>
     * 
     * Wait for the current element to be present (visibility not required). Throw exception if the
     * time passed exceeds {@code}waitTime{@code} passes or an error is detected
     * 
     * @param waitTime
     *            how long to wait for this element to become present
     * @param errorDetector
     *            class that can report error messages
     * @return true if the element is present and there is no error, throws exception otherwise
     */
    // boolean waitToPresent(long waitTime, ErrorDetector errorDetector);

    /**
     * Send any key from keyboard including special keys
     * 
     * @param key
     */
    void sendKeys(Keys key);

    /**
     * Wait for element to be displayed
     */
    void waitToBeDisplayed();

    /**
     * Wait for element to be hidden
     */
    void waitToBeHidden();

    /**
     * Wait for element to be enabled
     * 
     * @see WebElement#isEnabled()
     */
    void waitToBeEnabled();

    /**
     * Wait for element to be enabled
     * 
     * @param timeout
     * @see WebElement#isEnabled()
     */
    void waitToBeEnabled(long timeout);

    /**
     * Wait for element to be NOT enabled
     * 
     * @see WebElement#isEnabled()
     */
    void waitToBeDisabled();

    /**
     * Wait for element to be NOT enabled
     * 
     * @param timeout
     * @see WebElement#isEnabled()
     */
    void waitToBeDisabled(long timeout);

    /**
     * Wait for attribute to contain some string
     * 
     * @param attribute
     * @param partialValue
     */
    void waitForAttributeToContain(String attribute, String partialValue);

    /**
     * Wait for attribute to contain some string
     * 
     * @param attribute
     * @param partialValue
     * @param timeout
     */
    void waitForAttributeToContain(String attribute, String partialValue, long timeout);

    UIElement findUIElement(By by, String description);

    UIElement findUIElement(By by, String description, long timeout);

    List<UIElement> findUIElements(By by, String description);

    /**
     * Check if element is not in DOM now
     * 
     * @return true if element is not in DOM, false otherwise
     */
    boolean isAbsent();

    /**
     * Check if element is present in DOM
     * 
     * @return true if element is present, note that element can be hidden
     */
    boolean isPresent();

    /**
     * The inverse function of isDisplayed()
     * 
     * @return true if element is hidden, false if element is displayed
     */
    boolean isHidden();

    /**
     * @return WebElement object
     */
    WebElement getWebElement();

    /**
     * some elements cannot be clicked using regular click method.
     */
    void clickByJavascript();

    /**
     * Click on element without waiting for element to be displayed. This function will not throw
     * timeOutException if element is not displayed
     */
    void clickNoWait();

    /**
     * @return
     */
    UIActions getUIActions();

    /**
     * Keep on clicking element every {@code interval} until any of {@code expected} element is
     * displayed or {@code timeout} is reached.
     * 
     * @param expected
     *            Expected UIElement
     * @param timeout
     *            duration to wait in ms
     * @param intervals
     *            duration to pause between clicks in ms.
     * 
     */
    void clickUntilElementsDisplayed(List<UIElement> expected, long timeout, long intervals);

    void clickUntilElementsDisplayed(
            List<UIElement> expected,
            long timeout,
            long intervals,
            ErrorDetector errorDetector);

    /**
     * wait until the element has expected text
     * 
     * @param text
     * @param scope
     */
    void waitForMatchText(String text, SearchScope scope);

    /**
     * Select all options for multiple select node
     */
    void selectAllOptions();

    /**
     * Select additional option for multiple select node
     * 
     * @param text
     */
    void selectByVisibleText(String text);

    /**
     * Remove option from multiple select node
     * 
     * @param text
     */
    void deselectByVisibleText(String text);

    /**
     * Deselect all options for multiple select node
     */
    void deselectAllOptions();

    /**
     * @param by
     * @param description
     * @param timeout
     * @return List<UIElement> findUIElements(By by, String description, long timeout);
     * 
     *         /** Override the description set when the element was created.
     * 
     * @param description
     *            what this element should be called in logs from now on
     */
    void setDescription(String desc);

    /**
     * Return the description that should be used for this element in logs
     * 
     * @return description
     */
    public String getDescription();

    /**
     * @param by
     * @param description
     * @param timeout
     * @return
     */
    List<UIElement> findUIElements(By by, String description, long timeout);

    /**
     * Use this in order to handle file selection dialog <br>
     * This method will send keys to file input box <br>
     * For example {@code <input type="file" id="uploader" />}
     * 
     * @param keysToSend
     *            - file path
     */
    void sendKeysToFileInput(CharSequence... keysToSend);

    /**
     * @param errorDetector
     * @param timeout
     */
    void waitToBeHidden(ErrorDetector errorDetector, long timeout);

    /**
     * Check if div has text that may have been updated by JavaScript
     * 
     * @return True if text is present, and this element is displayed
     */
    boolean hasText();

    /**
     * Wait for element to be present in DOM
     * 
     * @param errorDetector
     * @param timeout
     */
    void waitToBePresent(ErrorDetector errorDetector, long timeout);

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element when the text is
     * displayed. Return an empty string if no text is displayed after default timeout.
     * 
     * @return The innerText of this element.
     */
    String getTextWhenDisplayed();

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element when the text is
     * displayed. Return an empty string if no text is displayed after specified timeout.
     * 
     * @param timeout
     * @return The innerText of this element.
     */
    String getTextWhenDisplayed(long timeout);

    /**
     * Wait for text to display
     */
    void waitForTextToDisplay();

    /**
     * Wait for text to display
     * 
     * @param timeout
     */
    void waitForTextToDisplay(long timeout);

    /**
     * Wait for the value of an input box to display
     */
    void waitForValueToDisplay();

    /**
     * Wait for the value of an input box to display
     * 
     * @param timeout
     */
    void waitForValueToDisplay(long timeout);	
	
}
