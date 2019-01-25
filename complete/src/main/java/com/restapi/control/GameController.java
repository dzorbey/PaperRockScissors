package com.restapi.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restapi.domain.ResponseObject;
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
    public ResponseObject play(@RequestHeader("UserSelection") @ApiParam(
            value = "The origin of the User Selection", defaultValue = "ROCK") Game selected) throws Exception {

//      source.validate(SourceComponent.TESTING, SourceComponent.MP_PORTAL, SourceComponent.UNIT_TEST,
    //      SourceComponent.MP_RTS);

  	  return gameService.play(selected);
    }
    

}
