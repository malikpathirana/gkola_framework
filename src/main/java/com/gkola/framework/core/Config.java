package com.gkola.framework.core;

public interface Config {
	  public String getValue(String key);

	    public Long getLongValue(String key, long defaultValue);

	    /**
	     * Set value in config.cfg
	     * 
	     * @param key
	     * @param value
	     */
	    void setValue(String key, String value);
}
