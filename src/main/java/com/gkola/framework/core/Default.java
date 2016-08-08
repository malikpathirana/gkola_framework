package com.gkola.framework.core;

public enum Default {
	CONFIG_PATH("/config/config.cfg"), 
    UI_TYPE("xpath"), 
    DRIVER_URL("localhost"), 
    DRIVER_HOST("localhost"), 
    DRIVER_PORT("4567"), 
    START_PAGE("http://google.com"), 
    DRIVER_BROWSER("firefox"),
    OS("Windows 7")
    ;

    public static int CONFIG_VAR_DEPTH = 3;

    private String value;

    private Default(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
