package com.gkola.framework.drivers;

import org.openqa.selenium.NoAlertPresentException;

import com.gkola.framework.util.SearchScope;

public interface UIAlert {
	 public static String NO_ALERT_FOUND = "";

	    /**
	     * @param message
	     *            - Expected message from Alert body
	     * @param scope
	     *            - Exact match or substring of Alert body
	     * @throws NoAlertPresentException
	     *             - if Alert not found
	     * @throws UIElementVerificationException
	     *             - if Alert message does not match expected {@code message}
	     */
	    public void acceptAlertMatchingMessage(String message, SearchScope scope)
	            throws NoAlertPresentException;

	    /**
	     * Accept Alert <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     */
	    void acceptAlertIfPresent();

	    /**
	     * Accept Alert <br>
	     * <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     * 
	     * @param timeout
	     *            - max timeout time to wait for alert to appear
	     * 
	     * 
	     */
	    void acceptAlertIfPresent(long timeout);

	    /**
	     * Dismiss Alert <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     */
	    void dismissAlertIfPresent();

	    /**
	     * Dismiss Alert <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     * 
	     * @param timeout
	     *            - max timeout time to wait for alert to appear
	     */
	    void dismissAlertIfPresent(long timeout);

	    /**
	     * Accept Alert <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     * 
	     * @return Alert message, or empty String if Alert is not found
	     */
	    String acceptAlertIfPresentAndGetMessage();

	    /**
	     * Accept Alert <br>
	     * <b>Note:</b> This method will pass even if Alert is not present
	     * 
	     * @param timeout
	     *            - max timeout time to wait for alert to appear
	     * @return - Alert message, or empty String if Alert is not found
	     */
	    String acceptAlertIfPresentAndGetMessage(long timeout);

	    /**
	     * @return Message from Alert body, or empty String if Alert was not found
	     */
	    public String getAlertMessage();

	    /*
	     * TODO This method is using AutoIT, it should go to windows util class<br> Checking for a
	     * presence of OS dialog box
	     * 
	     * @param activity
	     * 
	     * @param e
	     * 
	     * public void checkForModalDialog(String activity, Exception e);
	     */
}
