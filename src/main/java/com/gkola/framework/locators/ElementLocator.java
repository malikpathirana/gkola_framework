package com.gkola.framework.locators;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface ElementLocator {
	WebElement locateElement();

    /**
     * Calls WebElement.findElements()
     * 
     * @return
     */
    List<WebElement> locateElements();

    /**
     * This function allows user to dynamically define and use an element on the page. <br>
     * <br>
     * For example, if there is a userlink on the dashboard which is based on the username. The
     * definition for the element will be : <br>
     * <i> UIElement userLink = createElement(UIType.Xpath, "//span//div/a[text()= {1}]");</i><br>
     * and to convert it into "//span//div/a[text()= AdminName]" on the fly, call replaceValues() <br>
     * <i> userLink.replaceValues("AdminName").click();</i>
     * 
     * @param values
     *            - Value(s) to be replaced: for example to replace {1} {2} {3} with "String1"
     *            String2" "String3" in elements locator, one will call replaceValues("String1",
     *            "String2", "String3")
     */
    void replaceValues(String... values);
}
