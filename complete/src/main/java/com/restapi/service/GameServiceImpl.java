package com.restapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import com.restapi.domain.ResponseObject;
import com.restapi.enums.Game;
import com.restapi.enums.GameConditions;

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
    
    @Value("${api.test.variable}")
    String testVariable;
    
    
    
    @Override
    public Game computerMove() {
    	Collections.shuffle(computerList);
    	return computerList.get(new Random().nextInt(computerList.size()));	
    }
    
    
    
    @Override
    public ResponseObject play(Game userSelected) {
    	ResponseObject object = new ResponseObject();
    	object.setResult(testVariable);
    	
    	Game computerSelected = computerMove();
    	System.out.println("userSelected is : " + userSelected);
    	
    	Optional<Game> selected = Optional.of(userSelected).filter(s -> s == computerSelected);
    	
    	System.out.println("computerSelected is : " + computerSelected.name());
    	
    	
    	selected.ifPresent(s -> {
    		
    		System.out.println("try again, selected the same");
    		object.setResult("selected the same");
    	});
    	
    	
    	selected.orElseGet(() -> {
    		
    	
    		Game result = GameConditions.fromText(userSelected.name() + "_" + computerSelected.name()).isWin();
    		
    		System.out.println("selected differently, result : " + result.getText());
    		
    		
    		return Game.PAPER;
    	});
    	
    	
    	
    	
    	return object;
    }
}
