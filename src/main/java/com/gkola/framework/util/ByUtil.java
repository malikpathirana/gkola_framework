package com.gkola.framework.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ByUtil {
	 private static Logger logger = Logger.getLogger(ByUtil.class);

	    public static String getByStr(By by) {
	        return "{" + ((by == null) ? " null " : by.toString()) + "}";
	    }

	    /**
	     * Retrieving value from By
	     * 
	     * @param by
	     * @return
	     */
	    public static String getByValue(By by) {
	        if (by != null) {
	            String byStr = by.toString();
	            int index = byStr.indexOf(":");
	            if (index > 0)
	                return byStr.substring(index + 2, byStr.length()); // by.toString() generates extra
	                                                                   // space before value
	        }
	        return "";
	    }

	    /**
	     * Mechanism used to locate elements <br>
	     * Generating By by uiType and value
	     * 
	     * @param uiType
	     * @param value
	     * @return
	     */
	    public static By getBy(UIType uiType, String value) {
	        switch (uiType) {
	            case Xpath:
	                return By.xpath(value);
	            case ID:
	                return By.id(value);
	            case Name:
	                return By.name(value);
	            case Link:
	                return By.cssSelector("a[href='" + value + "']");
	            case LinkText:
	                return By.linkText(value);
	            case PartialLink:
	                return By.xpath("//a[contains(@href,'" + value + "')]");
	            case PartialLinkText:
	                return By.partialLinkText(value);
	            case TagName:
	                return By.tagName(value);
	            case Css:
	                return By.cssSelector(value);
	            case ClassName:
	                return By.className(value);
	            case Text:
	                return By.xpath(".//*[contains(text()," + concatenizeString(value) + ")]");
	            default:
	                return By.cssSelector(value);
	        }
	    }

	    /**
	     * Converting By to UIType
	     * 
	     * @param by
	     * @return
	     */
	    public static UIType getUITypeBy(By by) {
	        String byStr = by.toString();

	        if (byStr.startsWith("By.xpath:")) {
	            return UIType.Xpath;
	        }
	        if (byStr.startsWith("By.id:")) {
	            return UIType.ID;
	        }
	        if (byStr.startsWith("By.name:")) {
	            return UIType.Name;
	        }
	        if (byStr.startsWith("By.linkText:")) {
	            return UIType.LinkText;
	        }
	        if (byStr.startsWith("By.partialLink:")) {
	            return UIType.PartialLink;
	        }
	        if (byStr.startsWith("By.tagName:")) {
	            return UIType.TagName;
	        }
	        if (byStr.startsWith("By.cssSelector:")) {
	            return UIType.Css;
	        }
	        if (byStr.startsWith("By.className:")) {
	            return UIType.ClassName;
	        }
	        return UIType.Css;
	    }

	    /**
	     * Takes String and outputs a string that can be used in an xpath text comparison without
	     * breaking due to quotes or apostrophes.
	     * 
	     * ex: input string "john jacob jenglheimer schmidt" will output string
	     * "'john jacob jenglheimer schmidt'"
	     * 
	     * input string "john's book "about schmidt" was a huge success" will output string
	     * "concat('john',"'",'s book "about schmidt" was a huge success')"
	     * 
	     * @param toConcatenize
	     * @return
	     */
	    public static String concatenizeString(String toConcatenize) {
	        if (toConcatenize.contains("'")) {
	            String[] stringFragments = toConcatenize.split("'");
	            String concatenizedString = "concat('";
	            for (int i = 0; i <= stringFragments.length - 1; i++) {
	                concatenizedString += stringFragments[i];
	                if (i == stringFragments.length - 1) {
	                    concatenizedString += "')";
	                } else {
	                    concatenizedString += "',\"'\",'";
	                }
	            }
	            return concatenizedString;
	        } else {
	            return "'" + toConcatenize + "'";
	        }
	    }
}
