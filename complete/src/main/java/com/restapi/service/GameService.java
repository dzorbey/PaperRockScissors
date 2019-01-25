package com.restapi.service;

import com.restapi.domain.GameResponse;
import com.restapi.enums.Game;


public interface GameService {

    GameResponse play(Game selected);
    Game computerMove();
}
