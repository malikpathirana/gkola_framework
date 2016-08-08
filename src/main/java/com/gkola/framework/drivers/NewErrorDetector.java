package com.gkola.framework.drivers;

import org.apache.log4j.Logger;

public class NewErrorDetector implements ErrorDetector {

	 private static Logger logger = Logger.getLogger(NewErrorDetector.class);

	    
	    public void assertNoError() {}

	    
	    public void assertError(String expectedError)  {
	        try {
				throw new Exception("Assert dummy error: " + expectedError);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	    public String getError() {
	        return ErrorDetector.NO_ERROR;
	    }

}
