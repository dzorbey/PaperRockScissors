package com.restapi.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.restapi.domain.ResponseObject;
import com.restapi.enums.Game;
import com.restapi.service.GameService;

@Component
@Profile("test")
public class MockGameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @Value("${api.test.variable}")
    String testVariable;

    
    @Override
    public Game computerMove() {
    	
        return null;
    }

    
    @Override
    public ResponseObject play(Game userSelected) {
    	ResponseObject object = new ResponseObject();
    	object.setResult("mock service");
    	
        logger.debug("testing basic response");
        return object;
    }


}