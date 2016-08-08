package com.gkola.framework.core;

public enum Timeout {
	    WAIT_TIMEOUT(60000),
	    VERIFY_TIMEOUT(300000),
	    VERIFY_INTERVAL(0),
	    ELEMENT_TIMEOUT(3000),
	    ELEMENTS_TIMEOUT(3000),
	    PAGELOAD_TIMEOUT(0),
	    SLEEP_HALF_SEC(500),
	    SLEEP_1_SEC(1000),
	    SLEEP_5_SEC(5000),
	    SLEEP_15_SEC(15000),
	    SLEEP_1_MIN(60000),
	    SLEEP_2_MIN(120000),
	    SLEEP_5_MIN(300000);

	    long value;

	    private Timeout(long timeout) {
	        this.value = timeout;
	    }

	    public long getValue() {
	        return this.value;
	    }

}
