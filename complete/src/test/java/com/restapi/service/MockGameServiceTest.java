package com.restapi.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.enums.GameResults;
import com.restapi.service.mock.MockGameServiceImpl;

import java.util.Optional;

import junit.framework.Assert;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class MockGameServiceTest {

    private MockGameServiceImpl gameServiceImpl;
    
    
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        //computer selection
    }
    
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