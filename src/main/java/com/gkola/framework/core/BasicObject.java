package com.gkola.framework.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gkola.framework.elements.DefaultUIElement;
import com.gkola.framework.elements.DefaultUIElements;
import com.gkola.framework.locators.ElementLocator;
import com.gkola.framework.util.UIType;

public abstract class BasicObject extends FWDriverObject {
	private static Logger logger = Logger.getLogger(BasicObject.class);
    protected List<UIElement> elementList = new ArrayList<UIElement>();
  //  protected List<BasicPieceObject> pieceList = new ArrayList<BasicPieceObject>();
    private String currentPage;
    
    protected BasicObject(UIDriver driver) {
        super(driver);
        currentPage = this.getClass().getSimpleName();
    }

    /**
     * Returning locator by driver
     *
     * @param uiType
     * @param value
     * @return ElementLocator capable of finding desired element.
     */
    public abstract ElementLocator getLocator(UIType uiType, String value);

    /**
     * Implementing elements verification for page and piece Printing page/piece name and go through
     * elements and pieces recursively
     */
    public void verifyAllElements() {
        logger.debug("Verifying " + getClass().getName() + " ...");
        verificationElements
                .add("<<" + getClass().getName() + ">> (" + elementList.size() + ") elements");
        for (UIElement element : elementList) {
            element.waitToBePresent();
            verificationCount++;
            verificationElements.add(element.getLocator().toString());
        }
       /* for (BasicPieceObject piece : pieceList)
            piece.verifyAllElements();*/
    }

    /**
     * A method for element definition with element type and value This method will not instantiate
     * the WebElement or find the HTML element on the page. It will only create the object that
     * defines the expected location of the HTML element on the page. It employs lazy instantiation,
     * so the {@code}WebDriver.findElement(By){@code} is not called until it is needed by a method
     * such as {@link}UIElement.isPresent(){@link} or {@link}UIElement.isVisible(){@link};
     *
     * @param uiType
     *            what By method should we use? (For example xpath or CSS)
     * @param value
     *            what is the specification to find the element. For example: {@code}
     *            //div[contains(text(),'MyCourse')]{@code} or {@code}div#row1{@code}
     * @return a UIElement object that contains the information needed to find the element later.
     */
    public UIElement createElement(UIType uiType, String value) {
        return createElement(uiType, value, null, true);
    }

    /**
     * A method for element definition with element type, value and 'verify' 'verify' is a flag for
     * verifying the element in method 'verifyAllElements()'. if it's true, the element will be
     * automatically verified in the method. if it's false, the element will be skipped.
     *
     * @param uiType
     * @param value
     * @param verify
     * @return
     */
    public UIElement createElement(UIType uiType, String value, boolean verify) {
        return createElement(uiType, value, null, verify);
    }

    /**
     * @param uiType
     * @param value
     * @param description
     * @return
     */
    public UIElement createElement(UIType uiType, String value, String description) {
        return createElement(uiType, value, description, true);
    }

    /**
     * @param uiType
     * @param value
     * @param description
     * @param verify
     * @return
     */
    public UIElement createElement(UIType uiType, String value, String description, boolean verify) {
        ElementLocator locator = getLocator(uiType, value);
        UIElement ele = new DefaultUIElement(uiDriver, locator, description);
        if (verify)
            elementList.add(ele);
        return ele;
    }

    public UIElements createElements(UIType uiType, String value) {
        ElementLocator locator = getLocator(uiType, value);
        return new DefaultUIElements(uiDriver, locator);
    }

   /* public BasicObject addPiece(BasicPieceObject piece) {
        if (!piece.isEmpty()) {
            logger.info("Adding piece:" + piece.root.getLocator().toString() + " successfully!");
            pieceList.add(piece);
        }
        return this;
    }*/

    public void setCurrentPage(String page) {
        currentPage = page;
    }

    public String getCurrentPage() {
        return currentPage;
    }
}
