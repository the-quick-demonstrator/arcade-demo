package com.github.curriculeon.arcade.numberguess;

import com.github.curriculeon.arcade.GameInterface;
import com.github.curriculeon.arcade.PlayerInterface;
import com.github.curriculeon.utils.AnsiColor;
import com.github.curriculeon.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GameInterface {
    private List<PlayerInterface> numberGuessPlayers = new ArrayList<>();
    private IOConsole logger = new IOConsole(AnsiColor.CYAN);

    @Override
    public void add(PlayerInterface player) {
        numberGuessPlayers.add(player);
    }

    @Override
    public void remove(PlayerInterface player) {
        numberGuessPlayers.remove(player);
    }

    @Override
    public void run() {
        for (PlayerInterface player : numberGuessPlayers) {
            logger.println("Welcome to the Number Guess Dashboard!");
            int numberGuess = player.play();
            ThreadLocalRandom randomNumberGenerator = ThreadLocalRandom.current();
            int randomValue = randomNumberGenerator.nextInt(1, 10);
            if (numberGuess == randomValue) {
                logger.println("You guessed correctly!");
            } else {
                logger.println("Incorrect guess! The correct value was [ %s ]", randomValue);
            }
        }
    }
}