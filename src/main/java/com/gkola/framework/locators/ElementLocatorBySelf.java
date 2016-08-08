package com.gkola.framework.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ElementLocatorBySelf extends AbstractElementLocator{
    
	private WebElement self;
    private List<WebElement> selves;
    
    public ElementLocatorBySelf(WebElement self) {
        super(null);
        this.self = self;
    }

    public ElementLocatorBySelf(List<WebElement> selves) {
        super(null);
        this.selves = selves;
    }

    
    public WebElement locateElement() {
        try {
            self.isDisplayed();
            return self;
        } catch (StaleElementReferenceException ex) {
            return null;
        }
    }

    
    public List<WebElement> locateElements() {
        return selves;
    }

    @Override
    public String toString() {
        return (selves != null ? "s" : "");
    }

}
