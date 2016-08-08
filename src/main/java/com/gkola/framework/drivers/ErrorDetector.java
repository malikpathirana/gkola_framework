package com.gkola.framework.drivers;

public interface ErrorDetector {
	/**
     * Default value returned when no error found
     */
    public static final String NO_ERROR = "NO ERROR WAS FOUND";

    /**
     * Assert that none of the known error messages has appeared
     * 
     * @throws AssertionError
     *             if any known error message appeared
     */
    public void assertNoError() throws AssertionError;

    /**
     * Throws if expected error message is missing
     * 
     * @param expectedError
     * @throws AssertionError
     */
    public void assertError(final String expectedError) throws AssertionError;

    /**
     * @return first detected error message, or constant {@link #NO_ERROR} if no error was found.<br>
     *         NOTE: Empty string or NULL is a valid error message
     */
    public String getError();
}
