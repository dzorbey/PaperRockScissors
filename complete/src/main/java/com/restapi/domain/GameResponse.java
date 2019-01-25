package com.restapi.domain;

@AppPojo
public class GameResponse extends AbstractResponse {

	private String userSelection;
	private String computerSelection;
	
	public String getUserSelection() {
		return userSelection;
	}
	public void setUserSelection(String userSelection) {
		this.userSelection = userSelection;
	}
	public String getComputerSelection() {
		return computerSelection;
	}
	public void setComputerSelection(String computerSelection) {
		this.computerSelection = computerSelection;
	}
}
