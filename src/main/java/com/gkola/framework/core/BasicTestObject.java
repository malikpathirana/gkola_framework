package com.gkola.framework.core;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BasicTestObject extends FWDriverObject{
	private static final String DEFAULT = "DEFAULT";

    private static Logger logger = Logger.getLogger(BasicTestObject.class);

    private DesiredCapabilities desiredCapabilities = null;

    private String default_screen_shot_link;

    public static String testMethodName;

    public BasicTestObject() {
        super();
    }

    public BasicTestObject(WebDriver driver) {
       
    }
    
    
    @BeforeTest(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeTest(
            @Optional(DEFAULT) String browser,
         
            ITestContext context) {
        logger.info("======BEGIN Test workflow============");
        logger.info("BEGIN Test: " + context.getName());
        logger.info("======BEGIN Test workflow============");
        
       

    
        sameOrDefault(browser, ConfigKeys.KEY_BROWSER, config);
        
        String browserName = config.getValue(ConfigKeys.KEY_BROWSER.getKey());

        Browser browserType = Browser.valueOf(browserName.toUpperCase());
        multiplyTimeouts(browserType.getTimeout());
    }

    public void sameOrDefault(String param, ConfigKeys key, Config config) {
        if (!param.equals(DEFAULT)) {
            config.setValue(key.getKey(), param);
        }

    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext context) {
        logger.info("======END Test workflow============");
        logger.info("END Test: " + context.getName());
        logger.info("======END Test workflow============");
    }

    /**
     * Before each test method, use TestNG and Java Reflection to store and log the name of the
     * current test method.
     * 
     * @param method
     */
    @BeforeMethod(alwaysRun = true)
    public void logMethod(Method method) {
        logger.info("-------------BEGIN Test Method-------------------");
        logger.info("BEGIN Test Method: " + method.getName());
        logger.info("-------------BEGIN Test Method-------------------");
        testMethodName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method testMethod) {
        logger.info("-------------END Test Method-------------------");
        logger.info("END Test Method: " + testMethod.getName());
        logger.info("-------------END Test Method-------------------");
    }

    private void multiplyTimeouts(int timeoutMultiplier) {
        for (ConfigKeys cfgKey : ConfigKeys.values()) {
            if (cfgKey.toString().contains("TIMEOUT")) {
                String timeout = config.getValue(cfgKey.getKey());
                if (timeout != null && !timeout.isEmpty()) {
                    config.setValue(
                            cfgKey.getKey(),
                            "" + Long.parseLong(timeout) * timeoutMultiplier);
                }
            }
        }

    }

}
