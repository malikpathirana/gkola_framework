package com.gkola.framework.core;

import java.util.List;

import org.openqa.selenium.By;

public interface UIElements extends UIEleBase{

    UIElements replaceValues(String... values);

    /**
     * How many {@link UIElement} objects do we have in {@link UIElements}
     * 
     * @return
     */
    int length();

    List<UIElement> getUIElementsList();

    /**
     * Under current {@link UIElements}, get {@link UIElement} by index
     * 
     * @param index
     * @return {@link UIElement} or null if index is out of bound
     */
    UIElement getUIElementByIndex(int index);

    /**
     * Under current {@link UIElements}, get {@link UIElement} based on its innerText
     * 
     * @param text
     * @return {@link UIElement} or null if element is not in the list
     */
    UIElement getUIElementByText(String text);

    /**
     * Under current {@link UIElements}, get {@link UIElement} by index, and than get its child
     * using {@link By}
     * 
     * @param index
     * @param childBy
     * @return {@link UIElement} or null if index was out of bound
     */
    UIElement getChildUIElementByIndex(int index, By childBy);

    /**
     * Under current {@link UIElements}, get {@link UIElement} by index and then get List of its
     * child {@link UIElement}s using {@link By}
     * 
     * @param index
     * @param childBy
     * @return Elements under specific index found by {@code childBy} or null
     */
    List<UIElement> getChildUIElementsByIndex(int index, By childBy);

    /**
     * Under current {@link UIElements}, get {@link UIElement} by text and then get its child using
     * {@link By}
     * 
     * @param text
     * @param childBy
     * @return
     */
    UIElement getChildUIElementByText(String text, By childBy);

    /**
     * Under current {@link UIElements}, get {@link UIElement} by text and then get List of its
     * child {@link UIElement}s using {@link By}
     * 
     * @param text
     * @param childBy
     * @return
     */
    List<UIElement> getChildUIElementsByText(String text, By childBy);

    /**
     * Get all children under current {@link UIElements} using {@link By}
     * 
     * @param childBy
     * @return
     */
    List<UIElement> getAllChildUIElements(By childBy);

    /**
     * Wait for all elements in {@link UIElements} list to be gone from DOM
     * 
     */
    void waitToBeAbsent();

    /**
     * Wait for all in {@link #elements} list to be gone from DOM
     * 
     * @param timeout
     */
    void waitToBeAbsent(long timeout);

    /**
     * Wait for all {@link UIElements} to be hidden
     * 
     */
    void waitToBeHidden();

    /**
     * Wait for all {@link UIElements} to be hidden
     * 
     * @param timeout
     */
    void waitToBeHidden(long timeout);

    /**
     * Wait for all {@link UIElements} to be displayed
     * 
     */
    void waitToBeDisplayed();

    /**
     * Wait for all {@link UIElements} to be displayed
     * 
     * @param timeout
     */
    void waitToBeDisplayed(long timeout);

    /**
     * Wait for all {@link UIElements} to be in DOM
     * 
     * @param timeout
     * @throws TimeOutException
     *             if {@link #elements} list is null after timeout
     */
    void waitToBePresent(long timeout);

    /**
     * Wait for all {@link UIElements} to be in DOM
     * 
     * @throws TimeOutException
     *             if {@link #elements} list is null after timeout
     */
    void waitToBePresent();

    /**
     * Is list of {@link UIElements} initialized ?
     * 
     * @return false if {@link #elements} == null, true otherwise
     */
    boolean areInitialized();

    /**
     * Locate all {@link UIElements}
     */
    void initializeAll();

    /**
     * Locate all elements
     * 
     * @return false if list of {@link UIElements} is null, true otherwise
     */
    boolean doFindAll();

    /**
     * Locate all elements
     * 
     * @return false if list of {@link UIElements} is null, true otherwise
     */
    boolean areAllPresent();

    /**
     * Wait for {@code numberOfElements} to appear in {@link UIElements}
     * 
     * @param numberOfElements
     *            - minimum number of elements to be present in {@link UIElements}
     * @param timeout
     */
    void waitForNumberOfElementsToBePresent(int numberOfElements, long timeout);

    /**
     * Wait for {@code numberOfElements} to appear in {@link UIElements}
     * 
     * @param numberOfElements
     *            - minimum number of elements to be present in {@link UIElements}
     */
    void waitForNumberOfElementsToBePresent(int numberOfElements);

    /**
     * Are all {@link UIElements} displayed
     * 
     * @return true if all elements are visible, false otherwise
     */
    boolean areAllDisplayed();

    /**
     * Return a visible element in the elements group.
     *
     * @return
     */
    UIElement getOneDisplayedElement();

    /**
     * Return a visible element in the elements group in certain time.
     *
     * @return
     */
    UIElement getOneDisplayedElement(long timeout);
}
