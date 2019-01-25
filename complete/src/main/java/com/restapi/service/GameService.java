package com.restapi.service;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;

/*
 * Using a gameService interface definition,
 * using mock and real service implementations of the same interface, 
 * which can be extended further in implementation
 * 
 */
public interface GameService {

    GameResponse play(Game selected);
    Game computerMove();
}
