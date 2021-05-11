package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.services.AccountService;
import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ScreenRouter;
import com.revature.assigments.p0.util.UserTracker;

import java.io.BufferedReader;

public class CheckBalanceScreen extends Screen{

    private BufferedReader consoleReader;
    private AccountService AccountService;
    private ScreenRouter router;

    public CheckBalanceScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService, UserTracker userTracker) {
        super("CheckBalanceScreen","/checkBalance", userTracker);
        this.consoleReader = consoleReader;
        AccountService = accountService;
        this.router = router;
    }

    @Override
    public void render() {


        System.out.println("     << Check Balance Account >>     ");
        System.out.println("-------------------------------------");
        System.out.println("What account would you like to check the balance?");
        System.out.println("Array of Account in CheckBalance Screen is >> " + userTracker.getUser().getAccounts().size() );
        for (int i = 0; i <  userTracker.getUser().getAccounts().size() -1; i++){
            System.out.printf("%d.-Account#%d >>>  %s\n", i+1, userTracker.getUser().getAccounts().get(i).getId(), userTracker.getUserAccounts().get(i).getAccountType());
        }

        //accountType = consoleReader.readLine();
    }
}
