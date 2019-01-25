package com.restapi.domain;

@AppPojo
public class GameResponse {

	private String result;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
