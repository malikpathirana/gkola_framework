package com.gkola.framework.core;

public enum ConfigKeys {
	KEY_DRIVER_URL("driverurl"),
	KEY_BROWSER("browser"),
    KEY_START_PAGE_URL("startpageurl"),
    KEY_START_PAGE_CLASS("startpageclass"),
    KEY_CHROME_DRIVER("webdriver.chrome.driver"),
    KEY_CHROME_DRIVER_PATH("chrome.driver.path"),
    KEY_IE_DRIVER("webdriver.ie.driver"),
    KEY_IE_DRIVER_PATH("ie.driver.path"),

    KEY_WAIT_TIMEOUT("waittimeout"),
    KEY_VERIFY_TIMEOUT("verifytimeout"),
    KEY_VERIFY_INTERVAL("verifyinterval"),
    KEY_ELEMENT_TIMEOUT("elementtimeout"),
    KEY_ELEMENTS_TIMEOUT("elementstimeout"),
    KEY_PAGELOAD_TIMEOUT("pageloadtimeout"),

    KEY_MAX_FAILED_TEST_RETRY("maxfailedtestretry"),

    KEY_TESTLINK_URL("testlinkurl"),
    KEY_TESTLINK_DEVKEY("testlinkdevkey"),
    KEY_TESTLINK_PRJ("testlinkprj"),
    KEY_TESTLINK_PLATFORM("testlinkplatform"),
    KEY_TESTLINK_BUILD("testlinkbuild"),
    KEY_TESTLINK_TESTPLAN("testlinktestplan"),

    KEY_ZAP_USAGE("zap_usage"),
    KEY_ZAP_ACTIVE("zap_active"),
    KEY_ZAP_PROGRAM_LOCATION("zap_program_location"),
    KEY_ZAP_LOAD_TIME("zap_loadtime"),
    KEY_ZAP_PROXY_HOSTNAME("zap_proxy_hostname"),
    KEY_ZAP_PROXY_PORT("zap_proxy_port"),
    KEY_ZAP_DEBUG_MESSAGE("zap_debug_message"),
    KEY_ZAP_REPORTS_DIR("zap_reports_dir"),
    KEY_ZAP_ACTIVE_SCAN_RECURSE("zap_active_scan_recurse"),
    KEY_ZAP_ACTIVE_SCAN_IN_SCOPE_OMLY("zap_active_scan_in_scop_only"),
    KEY_ZAP_GET_ALERTS_FROM("zap_get_alerts_from"),
    KEY_ZAP_GET_ALERTS_TO("zap_get_alerts_to"),
    KEY_ZAP_SAVE_REPORTS("zap_save_reports"),

    KEY_APPIUM_TIMEOUT("appium_timeout"),
    KEY_APPIUM_VERSION("appium_version"),
    KEY_APPIUM_PORT("default_appium_port"),

    KEY_PERF_ACTIVE("performance_active"),
    KEY_PERF_PORT("performance_port"),

    KEY_ANDROID_X86_IP("androidx86_ip"),
    KEY_ANDROID_X86_PORT("androidx86_port"),
    KEY_ANDROID_SERIAL_ID("android_serial_id"),
    KEY_ADB_LOCATION("adb_location"),
    KEY_ADB_TIMEOUT("adb_timeout"),
    KEY_OS("os"),
    KEY_PLATFORM("platform"),
    KEY_BROWSER_VERSION("browser_version"),
    KEY_BROWSER_NAME("browser_name"),
    KEY_ORIENTATION("orientation"),
    KEY_FORCE_DRIVER_DOWNLOAD("force_download"),
    KEY_SKIP_DRIVER_DOWNLOAD("skip_download"),

    KEY_MOBILE_PLATFORM_NAME("mobile_platform_name"),
    KEY_MOBILE_DEVICE_NAME("mobile_device_name"), 
    KEY_SAUCELABS_TIMEOUT("saucelabs_timeout");

    String key;

    private ConfigKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
