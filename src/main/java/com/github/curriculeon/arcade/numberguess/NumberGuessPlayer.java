package com.github.curriculeon.arcade.numberguess;

import com.github.curriculeon.arcade.ArcadeAccount;
import com.github.curriculeon.arcade.PlayerInterface;
import com.github.curriculeon.utils.AnsiColor;
import com.github.curriculeon.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer implements PlayerInterface {
    private ArcadeAccount arcadeAccount;
    private IOConsole logger = new IOConsole(AnsiColor.CYAN);

    public NumberGuessPlayer(ArcadeAccount arcadeAccount) {
        this.arcadeAccount = arcadeAccount;
    }

    @Override
    public ArcadeAccount getArcadeAccount() {
        return arcadeAccount;
    }

    @Override
    public Integer play() {
        return logger.getIntegerInput("Enter a number between 1 and 10");
    }
}