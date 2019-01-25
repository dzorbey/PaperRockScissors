package com.restapi.enums;

import java.util.Arrays;

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