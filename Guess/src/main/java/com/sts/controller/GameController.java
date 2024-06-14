package com.sts.controller;

import com.sts.service.GameService;
import com.sts.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUtils gameUtils;

    @GetMapping("/game-home")
    public String getHomepage(@RequestParam(value = "Guessletter", required = false) String guessLetter, Model model) {
        System.out.println("Guessed character:" + guessLetter);

        if (guessLetter != null && !guessLetter.isEmpty()) {
            boolean addGuess = gameService.addGuess(guessLetter.charAt(0));
            if (!addGuess) {
                gameUtils.reduceTry();
            }
        }

        model.addAttribute("wordtodisplay", gameService.toString());
        model.addAttribute("triesLeft", gameUtils.getRemainingTries());

        if (gameService.isWon()) {
            model.addAttribute("message", "You have won! Reloading the game...");
            return "win-page";
        } else if (gameUtils.getRemainingTries() <= 0) {
            model.addAttribute("message", "Game Over! Reloading the game...");
            return "lose-page";
        }

        return "game-home-page";
    }

    @GetMapping("/reload")
    public String reload() {
        gameService.reloadGame();
        gameUtils.resetTries();
        return "redirect:/game-home";
    }
}
