package com.restapi.service;

import com.restapi.domain.ResponseObject;
import com.restapi.enums.Game;


public interface GameService {

    ResponseObject play(Game selected);
    
    Game computerMove();
}
