package com.restapi.enums;

import java.util.Arrays;

/*
 * Defining game results in enumaration, these static mappings could be retrieved from the api.properties file.
 * the file exists already, and i believe can easily be converted to be so later on. this suffice the need at the moment.
 */
public enum GameResults {
	
	    COMPUTER_WIN("Computer Wins.."),
	    USER_WIN("Congratulations, You Win.."),	    
	    NO_WIN("Selected the same, try again..");
	        
	    private String text;

	    GameResults(String text) {
	        this.text = text;
	    }
	    
	    public String getText() {
	        return this.text;
	    }
	    
	    public static GameResults fromText(String text) {
	    	return Arrays.stream(values())
	          .filter(bl -> bl.text.equalsIgnoreCase(text))
	          .findFirst()
	          .orElse(null);
	    }
}