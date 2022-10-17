package com.github.curriculeon.arcade;

import com.github.curriculeon.utils.AnsiColor;
import com.github.curriculeon.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class ArcadeAccountManager {
    private IOConsole logger = new IOConsole(AnsiColor.GREEN);

    private List<ArcadeAccount> arcadeAccountList = new ArrayList<>();

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public ArcadeAccount getAccount(String accountName, String accountPassword) {
        String preLogMessage = "Attempting to retrieve account with account name of [ %s ] and password of [ %s ]";
        logger.println(preLogMessage, accountName, accountPassword);
        for (ArcadeAccount arcadeAccount : arcadeAccountList) {
            boolean hasCorrectName = arcadeAccount.getAccountName().equalsIgnoreCase(accountName);
            boolean hasCorrectPass = arcadeAccount.getAccountPassword().equalsIgnoreCase(accountPassword);
            boolean hasCorrectCredentials = hasCorrectName && hasCorrectPass;
            if (hasCorrectCredentials) {
                String postLogMessage = "Successfully retrieved account with account name of [ %s ] and password of [ %s ]";
                logger.println(postLogMessage, accountName, accountPassword);
                return arcadeAccount;
            }
        }
        String postLogMessage = "Failed to retrieve account with account name of [ %s ] and password of [ %s ]";
        logger.println(postLogMessage, accountName, accountPassword);
        return null;
    }

    /**
     * logs & creates a new `ArcadeAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public ArcadeAccount createAccount(String accountName, String accountPassword) {
        String preLogMessage = "Attempting to create account with name of [ %s ] and password of [ %s ]";
        logger.println(preLogMessage, accountName, accountPassword);

        ArcadeAccount arcadeAccount = new ArcadeAccount(accountName, accountPassword);

        String postLogMessage = "Successfully created account of [ %s ]";
        logger.println(postLogMessage, arcadeAccount.toString());
        return arcadeAccount;
    }

    /**
     * logs & registers a new `ArcadeAccount` to `this.getArcadeAccountList()`
     *
     * @param arcadeAccount the arcadeAccount to be added to `this.getArcadeAccountList()`
     */
    public void registerAccount(ArcadeAccount arcadeAccount) {
        String preLogMessage = "Attempting to register account [ %s ] to ArcadeAccountManager";
        logger.println(preLogMessage, arcadeAccount.toString());

        arcadeAccountList.add(arcadeAccount);

        String postLogMessage = "Successfully registered account [ %s ] to ArcadeAccountManager";
        logger.println(postLogMessage, arcadeAccount.toString());
    }
}
