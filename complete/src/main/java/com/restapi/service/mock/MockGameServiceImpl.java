package com.restapi.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.enums.GameConditions;
import com.restapi.enums.GameResults;
import com.restapi.service.GameService;

@Component
@Profile("test")
public class MockGameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Game computerMove;
    
    public MockGameServiceImpl(Game computerMove) {
    	this.computerMove = computerMove;
	}
    
    @Override
    public Game computerMove() {    
        return this.computerMove;
    }

	@Override
    public GameResponse play(Game userSelected) {
    	GameResponse response = new GameResponse();
    	
    	Game computerSelected = computerMove();
    	response.setComputerSelection(computerSelected.name());
		response.setUserSelection(userSelected.name());
    	
    	GameResults result = GameConditions.fromText(userSelected.name() + "_" + computerSelected.name()).getResult();
    	response.setMessage(result.name());
    	response.setSuccess(true);
    	
    	return response;
    }
}