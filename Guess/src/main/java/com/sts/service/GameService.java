package com.sts.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    
    private String randomlyChosenWord;
    private char[] charsOfWord;
    private boolean isWon;

    private String[] randomWords = {"father", "java", "mother", "baby", "tiger"};
    private Random random = new Random();

    public GameService() {
        reloadGame();
    }

    public void reloadGame() {
        randomlyChosenWord = randomWords[random.nextInt(randomWords.length)];
        charsOfWord = new char[randomlyChosenWord.length()];
        isWon = false;
        System.out.println(randomlyChosenWord);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (char c : charsOfWord) {
            if (c == '\u0000') {
                ret.append("_");
            } else {
                ret.append(c);
            }
            ret.append(' ');
        }
        return ret.toString().trim();
    }

    public boolean addGuess(char guessed) {
        boolean isGuessedCorrect = false;
        for (int i = 0; i < randomlyChosenWord.length(); i++) {
            if (guessed == randomlyChosenWord.charAt(i)) {
                charsOfWord[i] = guessed;
                isGuessedCorrect = true;
            }
        }
        checkWin();
        return isGuessedCorrect;
    }

    public boolean isWon() {
        return isWon;
    }

    private void checkWin() {
        for (char c : charsOfWord) {
            if (c == '\u0000') {
                return;
            }
        }
        isWon = true;
    }
}
