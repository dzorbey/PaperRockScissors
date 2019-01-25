package com.restapi.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restapi.domain.AbstractResponse;
import com.restapi.enums.Game;
import com.restapi.service.GameService;
import com.restapi.utility.Utility;

@RestController
@RequestMapping(value = "/game", produces = { "application/json" })
@Api(value = "Paper-Rock-Scissors", description = "Paper-Rock-Scissors Game Operations")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, value = "/play")
	@ApiOperation(value = "getGameResponse", notes = "Gameplay")
	public AbstractResponse play(
			@RequestHeader("inputHeader") @ApiParam(value = "The origin of the User Selection", defaultValue = "ROCK") Game selected)
			throws Exception {

		try {
			return gameService.play(selected);
		} catch (Exception e1) {
			return Utility.error(e1.getMessage());
		}
	}
}