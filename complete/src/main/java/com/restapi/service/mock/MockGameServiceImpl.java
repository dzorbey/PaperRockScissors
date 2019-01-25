package com.restapi.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.service.GameService;

@Component
@Profile("test")
public class MockGameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Game computerMove() {    	
        return null;
    }

    
    @Override
    public GameResponse play(Game userSelected) {
    	GameResponse object = new GameResponse();
    	object.setMessage("mock service");
    	
        logger.debug("testing basic response");
        return object;
    }


}
