package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.services.AccountService;
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

    }
}
