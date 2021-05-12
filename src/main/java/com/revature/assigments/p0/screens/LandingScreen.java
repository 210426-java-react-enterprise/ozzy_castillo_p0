package com.revature.assigments.p0.screens;

import java.io.BufferedReader;
import com.revature.assigments.p0.util.ScreenRouter;
import static com.revature.assigments.p0.Driver.app;

/**
 * Class to render the Landing Bank screen
 *
 */

public class LandingScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public LandingScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LandingScreen","/landing");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("      Welcome to CANAIMA BANK");
        System.out.println("-------------------------------------");
        System.out.println("1.-Sign Up");
        System.out.println("2.-Sign In");
        System.out.println("3.-Exit Application");

        try{
            System.out.printf(">> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1": //Sign Up
                    router.navigate("/signUp");
                    break;
                case "2": //Sign In
                    router.navigate("/signIn");
                    break;
                case "3": // Exit App
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid Selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
