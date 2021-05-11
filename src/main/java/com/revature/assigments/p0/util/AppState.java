package com.revature.assigments.p0.util;

import com.revature.assigments.p0.daos.AccountDAO;
import com.revature.assigments.p0.daos.UserDAO;
import com.revature.assigments.p0.screens.*;
import com.revature.assigments.p0.services.AccountService;
import com.revature.assigments.p0.services.UserService;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class to handle the running state of the app
 */
public class AppState {

    private boolean appRunning;
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserTracker userTracker;

    public AppState() {
        System.out.println("Initializing application");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDAO = new UserDAO();
        final UserService userService = new UserService(userDAO);
        final AccountDAO accountDAO = new AccountDAO();
        final AccountService accountService = new AccountService(accountDAO);


        router = new ScreenRouter();
        router.addScreen(new LandingScreen(consoleReader,router))
                .addScreen(new SignUpScreen(consoleReader, userService))
                .addScreen(new SignInScreen(consoleReader, router, userService, accountService))
                .addScreen(new TransactionScreen(consoleReader, router, userTracker))
                .addScreen(new CreateAccountScreen(consoleReader, router, accountService, userTracker))
                .addScreen(new CheckBalanceScreen(consoleReader, router, accountService ,userTracker)); // Here I can add more screens

        System.out.println("Application initialized!");
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public void setAppRunning(boolean appRunning){
        this.appRunning = appRunning;
    }
}

