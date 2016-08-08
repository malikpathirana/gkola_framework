package com.gkola.framework.core;

public enum Browser {
	    FIREFOX(1),
	    CHROME(2),
	    INTERNETEXPLORER(3),
	    SAFARI(2),
	    OPERA(1),
	    ANDROID(2),
	    IPAD(3),
	    IPHONE(3),
	    HTMLUNIT(1),
	    HTMLUNITWITHJS(1);

	    private int timeoutValue;

	    Browser(int value) {
	        timeoutValue = value;
	    }

	    Browser() {
	        timeoutValue = 1;
	    }

	    public int getTimeout() {
	        return timeoutValue;
	    }

}
