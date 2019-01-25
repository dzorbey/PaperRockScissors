package com.restapi.service;

import org.junit.Test;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.enums.GameResults;
import com.restapi.service.mock.MockGameServiceImpl;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class MockGameServiceTest {

    private MockGameServiceImpl gameServiceImpl;
     
    /*
     * Testing the game logic by each condition.
     */
    @Test
    public void shouldGameConditionsSatisfied() throws Exception {
        
    	//computer selection
    	gameServiceImpl = new MockGameServiceImpl(Game.ROCK);
        //user selection
    	GameResponse response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));

    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));
    	
    	//computer selection
    	gameServiceImpl=new MockGameServiceImpl(Game.PAPER);
    	//user selection
    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));

    	
    	//computer selection
    	gameServiceImpl=new MockGameServiceImpl(Game.SCISSORS);
    	//user selection
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));

    }
}