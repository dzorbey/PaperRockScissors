package com.restapi.enums;

import java.util.Arrays;

/*
 * Defining game return conditinos, this is where the logic of the game lies in and further on can be extended with changing conditions
 * without touching the gameService, and only updating the Mock gameService with associated testing features.
 */
public enum GameConditions {

	PAPER_ROCK(Game.PAPER + "_" + Game.ROCK, GameResults.USER_WIN), 
	PAPER_SCISSORS(Game.PAPER + "_" + Game.SCISSORS, GameResults.COMPUTER_WIN),
    PAPER_PAPER(Game.PAPER + "_" + Game.PAPER, GameResults.NO_WIN),

	ROCK_SCISSORS(Game.ROCK + "_" + Game.SCISSORS, GameResults.USER_WIN), 
	ROCK_PAPER(Game.ROCK + "_" + Game.PAPER, GameResults.COMPUTER_WIN),
	ROCK_ROCK(Game.ROCK + "_" + Game.ROCK, GameResults.NO_WIN),
	
	SCISSORS_ROCK(Game.SCISSORS + "_" + Game.ROCK, GameResults.COMPUTER_WIN), 
	SCISSORS_PAPER(Game.SCISSORS + "_" + Game.PAPER, GameResults.USER_WIN),
	SCISSORS_SCISSORS(Game.SCISSORS + "_" + Game.SCISSORS, GameResults.NO_WIN);

	private String text;
	private GameResults result;

	private Game myGame;
	
	
	GameConditions(String text, GameResults result) {
		this.text = text;
		this.result = result;
	}

	public GameResults getResult() {
		return result;
	}

	public static GameConditions fromText(String text) {
		return Arrays.stream(values())
				.filter(bl -> bl.text.equalsIgnoreCase(text)).findFirst()
				.orElse(null);
	}
}