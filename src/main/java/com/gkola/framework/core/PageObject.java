package com.gkola.framework.core;

public interface PageObject {
	

    /**
     * Go to web page
     * 
     * @param url
     */
    public void setUrl(String url);

    /**
     * Get initial URL
     * 
     * @return - start url
     */
    public String getUrl();

    /**
     * Go to url specified in config file
     */
    public void start();
}
