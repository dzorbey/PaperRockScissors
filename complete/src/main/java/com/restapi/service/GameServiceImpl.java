package com.restapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.enums.GameConditions;
import com.restapi.enums.GameResults;


/*
 * GameService, which simulates a randomly selected pick via computer, and matches against the user selection.
 */
@Profile("default")
@Controller
public class GameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    static final List<Game> computerList = new ArrayList<Game>() {
		private static final long serialVersionUID = 1L;
	{
    	add(Game.ROCK);
    	add(Game.PAPER);
    	add(Game.SCISSORS);
    }};
    
    
    @Override
    public Game computerMove() {
    	Collections.shuffle(computerList);
    	return computerList.get(new Random().nextInt(computerList.size()));	
    }
    
    
    @Override
    public GameResponse play(Game userSelected) {
    	GameResponse response = new GameResponse();
    	
    	Game computerSelected = computerMove();
    	System.out.println("userSelected is : " + userSelected);
    	System.out.println("computerSelected is : " + computerSelected.name());
    	
    	response.setComputerSelection(computerSelected.name());
		response.setUserSelection(userSelected.name());
    	
    	GameResults result = GameConditions.fromText(userSelected.name() + "_" + computerSelected.name()).getResult();
    	System.out.println("Game result [testing_again]: " + result.name());
    	response.setMessage("updated for testing : " + result.name());
    	response.setSuccess(true);
    	
    	return response;
    }
}