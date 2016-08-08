package com.gkola.framework.util;


	public enum UIType {
	    /**
	     * The value of the "id" attribute to search for
	     */
	    ID,

	    /**
	     * The value of the "name" attribute to search for
	     */
	    Name,

	    /**
	     * The xpath to use
	     */
	    Xpath,

	    /**
	     * Locates element using By.xpath(".//*[contains(text(),'" + value + "')]")
	     */
	    Text,

	    /**
	     * Finds elements via the driver's underlying W3 Selector engine. If the browser does not
	     * implement the Selector API, a best effort is made to emulate the API. In this case, we strive
	     * for at least CSS2 support, but offer no guarantees.
	     */
	    Css,

	    /**
	     * Locates elements by their tag name
	     */
	    TagName,

	    /**
	     * Locates element using By.cssSelector("a[href='" + value + "']");
	     */
	    Link,

	    /**
	     * Locates A elements by the exact text it displays
	     */
	    LinkText,

	    /**
	     * Locates A elements that contain the given link text
	     */
	    PartialLinkText,

	    /**
	     * Finds elements based on the value of the "class" attribute. If an element has many classes
	     * then this will match against each of them. For example if the value is "one two onone", then
	     * the following "className"s will match: "one" and "two"
	     */
	    ClassName,

	    /**
	     * Locates element using By.xpath("//a[contains(@href,'" + value + "')]")
	     */
	    PartialLink,

	}

