package com.github.curriculeon;

import com.github.curriculeon.arcade.ArcadeAccount;
import com.github.curriculeon.arcade.ArcadeAccountManager;
import com.github.curriculeon.arcade.Game;
import com.github.curriculeon.arcade.Player;
import com.github.curriculeon.arcade.numberguess.NumberGuessGame;
import com.github.curriculeon.arcade.numberguess.NumberGuessPlayer;
import com.github.curriculeon.arcade.slots.SlotsGame;
import com.github.curriculeon.arcade.slots.SlotsPlayer;
import com.github.curriculeon.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class ApplicationRunner implements Runnable {
    private static final IOConsole console = new IOConsole();

    @Override
    public void run() {
        String arcadeDashBoardInput;
        do {
            arcadeDashBoardInput = getArcadeDashboardInput();
            ArcadeAccountManager arcadeAccountManager = new ArcadeAccountManager();
            if ("select-game".equals(arcadeDashBoardInput)) {
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                ArcadeAccount arcadeAccount = arcadeAccountManager.getAccount(accountName, accountPassword);
                boolean isValidLogin = arcadeAccount != null;
                if (isValidLogin) {
                    String gameSelectionInput = getGameSelectionInput().toUpperCase();
                    if (gameSelectionInput.equals("SLOTS")) {
                        playSlots();
                    } else if (gameSelectionInput.equals("NUMBERGUESS")) {
                        playNumberGuess();
                    } else {
                        String errorMessage = "[ %s ] is an invalid game selection";
                        throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                    }
                } else {
                    String errorMessage = "[ %s ] is an invalid password for account [ %s ]";
                    throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
                }
            } else if ("create-account".equals(arcadeDashBoardInput)) {
                console.println("Welcome to the account-creation screen.");
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                arcadeAccountManager.createAccount(accountName, accountPassword);
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ create-account ], [ select-game ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ]")
                .toString());
    }

    private void playNumberGuess() {
        Player player = new NumberGuessPlayer();
        Game game = new NumberGuessGame();
        game.add(player);
        game.run();
    }

    private void playSlots() {
        Player player = new SlotsPlayer();
        Game game = new SlotsGame();
        game.add(player);
        game.run();
    }
}
