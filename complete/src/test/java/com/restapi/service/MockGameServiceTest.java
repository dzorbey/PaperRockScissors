package com.restapi.service;


import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.enums.GameResults;
import com.restapi.service.mock.MockGameServiceImpl;


public class MockGameServiceTest {

    private MockGameServiceImpl gameServiceImpl;
     
    /*
     * Testing the game logic by each condition.
     */
    //@Test
    public void shouldGameConditionsSatisfied() throws Exception {
       /* 
    	gameServiceImpl = new MockGameServiceImpl(Game.ROCK);
        GameResponse response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));

    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));
    	
    	gameServiceImpl=new MockGameServiceImpl(Game.PAPER);
    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));

    	
    	gameServiceImpl=new MockGameServiceImpl(Game.SCISSORS);
    	response = gameServiceImpl.play(Game.SCISSORS);
    	assertThat(response.getMessage(), is(equalTo(GameResults.NO_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.ROCK);
    	assertThat(response.getMessage(), is(equalTo(GameResults.USER_WIN.toString())));
    	
    	response = gameServiceImpl.play(Game.PAPER);
    	assertThat(response.getMessage(), is(equalTo(GameResults.COMPUTER_WIN.toString())));
*/
    }
}