package com.sts.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.sts.service.GameService;

@Component
public class GameUtils {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    private int MAX_TRIES = 5;

    public int reduceTry() {
        MAX_TRIES -= 1;
        return MAX_TRIES;
    }

    public int getRemainingTries() {
        return MAX_TRIES;
    }

    public void resetTries() {
        MAX_TRIES = 5;
    }

    public GameService reload() {
        GameService gameService = applicationContext.getBean(GameService.class);
        return gameService;
    }
}
