package com.restapi.integration.enums;

import java.util.Arrays;

public enum Preferences {

	MANUAL_PERMIT("Permit Data Loss"),
	PERMIT_DATA_LOSS("--permit-data-loss"),
    DATA("data"),
	MISSING_FILTER_SIZE(String.valueOf(0));

	private String text;

    Preferences(String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
    
    public static Preferences fromText(String text) {
    	return Arrays.stream(values())
          .filter(bl -> bl.text.equalsIgnoreCase(text))
          .findFirst()
          .orElse(null);
    }
}