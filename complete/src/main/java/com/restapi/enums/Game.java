package com.restapi.enums;

import io.swagger.annotations.ApiModel;
import java.util.Arrays;


/** 
 * Define the game as a high level enumaration.
 * 
 */
@ApiModel
public enum Game {
	
	    ROCK("ROCK"),
	    PAPER("ROCK"),
	    SCISSORS("SCISSORS");
	    
	    private String text;

	    Game(String text) {
	        this.text = text;
	    }
	    
	    public String getText() {
	        return this.text;
	    }
	    
	    public static Game fromText(String text) {
	    	return Arrays.stream(values())
	          .filter(bl -> bl.text.equalsIgnoreCase(text))
	          .findFirst()
	          .orElse(null);
	    }
}