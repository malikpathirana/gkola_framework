package com.gkola.framework.elements;

import com.gkola.framework.core.UIElement;

public interface UIActions {
	 /**
     * Click on element by location
     */
    public void clickOnLocation();

    /**
     * Hover over an element
     * 
     */
    public void hoverOver();

    /**
     * Hover element and click on it
     * 
     */
    public void hoverOverAndClickOn();

    /**
     * Send a CharSequence to the element.
     * 
     * @param keysToSend
     */
    void sendKeys(CharSequence... keysToSend);

    /**
     * Use actions to move the mouse to the element
     * 
     * @param elem
     */
    void mouseMoveHere();

    /**
     * Simulate mouse double click
     * 
     * 
     * @param elem
     */
    void doubleClick();

    /**
     * Move mouse to current element and click
     */
    void click();

    /**
     * Drop current element into {@code target} element
     * 
     * @param target
     *            - droppable uielement
     */
    void dragAndDrop(UIElement target);

    /**
     * see
     * {@link org.openqa.selenium.interactions.Actions#contextClick(org.openqa.selenium.WebElement)}
     */
    void rightClick();

    /**
     * see {@link Actions#dragAndDropBy(org.openqa.selenium.WebElement, int, int)}
     * 
     * @param xOffset
     * @param yOffset
     */
    void dragAndDrop(int xOffset, int yOffset);

    /**
     * see {@link Actions#clickAndHold(org.openqa.selenium.WebElement)}
     */
    void clickAndHold();

    /**
     * see {@link Actions#release(org.openqa.selenium.WebElement)}
     */
    void release();

    /**
     * Used to trigger a pop up menu and select an item from it.
     * 
     * @param hoverTarget
     *            element that triggers the menu
     * @param hoverName
     *            name to use in instructions and error messages
     * @param button
     *            element in the menu to select
     * @param buttonName
     *            name to use in instructions and error messages
     */
    // public void hoverOverOneAndClickOnOther(
    // WebElement hoverTarget,
    // String hoverName,
    // WebElement button,
    // String buttonName);

    /**
     * Send a single key from keyboard
     * 
     * @param thekey
     */
    // void pressKey(Keys thekey);
}
