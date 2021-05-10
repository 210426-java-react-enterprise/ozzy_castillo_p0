package com.revature.assigments.p0.util;

import com.revature.assigments.p0.daos.UserDAO;
import com.revature.assigments.p0.screens.LandingScreen;
import com.revature.assigments.p0.screens.SignInScreen;
import com.revature.assigments.p0.screens.SignUpScreen;
import com.revature.assigments.p0.screens.TransactionScreen;
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

        router = new ScreenRouter();
        router.addScreen(new LandingScreen(consoleReader,router))
                .addScreen(new SignUpScreen(consoleReader, userService))
                .addScreen(new SignInScreen(consoleReader, router, userService))
                .addScreen(new TransactionScreen(consoleReader, router, userTracker)); // Here I can add more screens

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

