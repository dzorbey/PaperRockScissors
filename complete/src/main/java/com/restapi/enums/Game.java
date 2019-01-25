package com.restapi.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

@ApiModel
public enum Game {
	
	    ROCK("ROCK"),
	    PAPER("ROCK"),
	    SCISSORS("SCISSORS"),
	    
	    @ApiModelProperty(hidden = true)
	    COMPUTER_WIN("Computer Wins.."),
	    
	    @ApiModelProperty(hidden = true)
	    USER_WIN("Congratulations, You Wins..");
	    
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