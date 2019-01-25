package com.restapi.enums;

import java.util.Arrays;

public enum GameConditions {

	PAPER_ROCK(Game.PAPER + "_" + Game.ROCK, Game.USER_WIN), PAPER_SCISSORS(
			Game.PAPER + "_" + Game.SCISSORS, Game.COMPUTER_WIN),

	ROCK_SCISSORS(Game.ROCK + "_" + Game.SCISSORS, Game.USER_WIN), ROCK_PAPER(
			Game.ROCK + "_" + Game.PAPER, Game.COMPUTER_WIN),

	SCISSORS_ROCK(Game.SCISSORS + "_" + Game.ROCK, Game.COMPUTER_WIN), SCISSORS_PAPER(
			Game.SCISSORS + "_" + Game.PAPER, Game.USER_WIN);

	private String text;
	private Game win;

	GameConditions(String text, Game win) {
		this.text = text;
		this.win = win;
	}

	public Game isWin() {
		return win;
	}

	public static GameConditions fromText(String text) {
		return Arrays.stream(values())
				.filter(bl -> bl.text.equalsIgnoreCase(text)).findFirst()
				.orElse(null);
	}
}