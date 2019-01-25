package com.restapi.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;
import com.restapi.service.GameService;

@RestController
@RequestMapping("/game")
@Api(value="Paper-Rock-Scissors", description="Paper-Rock-Scissors Game Operations")
public class GameController {

	@Autowired
    private GameService gameService;    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/play")
    @ApiOperation(value = "getGameResponse",
        notes = "Gameplay")
    public GameResponse play(@RequestHeader("UserSelection") @ApiParam(
            value = "The origin of the User Selection", defaultValue = "ROCK") Game selected) throws Exception {

  	  return gameService.play(selected);
    }
}
