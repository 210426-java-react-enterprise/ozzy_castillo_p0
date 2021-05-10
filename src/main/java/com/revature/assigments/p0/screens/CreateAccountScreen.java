package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.services.UserService;
import com.revature.assigments.p0.util.ScreenRouter;
import com.revature.assigments.p0.util.UserTracker;

import java.io.BufferedReader;

import static com.revature.assigments.p0.Driver.app;

public class CreateAccountScreen extends Screen{

    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;


    public CreateAccountScreen(BufferedReader consoleReader, ScreenRouter router, UserTracker userTracker) {
        super("TransactionScreen","/transaction",userTracker);
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        System.out.println("            <<  >>            ");
        System.out.println("-------------------------------------");
        System.out.println("1.-Create Account");
        System.out.println("2.-Check Balance");
        System.out.println("3.-Withdraw");
        System.out.println("4.-Transfer");
        System.out.println("5.-Transactions history");
        System.out.println("6.-Exit Application");

        //System.out.println("The current user id is >>>" +userTracker.getUser().getId());


        try{
            System.out.println(">> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1": //Create Account
                    System.out.println("Navigating >>>> Create Account");
                    router.navigate("createAccount", this.userTracker);
                    break;
                case "2": //Check Balance
                    System.out.println("Navigating >>>> Check Balance");
                    router.navigate("/checkBalance");
                    break;
                case "3": //Withdraw
                    System.out.println("Navigating >>>> Withdraw");
                    router.navigate("/withdraw");
                    break;
                case "4": //Transfer
                    System.out.println("Navigating >>>> Transfer");
                    router.navigate("/transfer");
                    break;
                case "5": //Transaction history
                    System.out.println("Navigating >>>> Transaction history");
                    router.navigate("/transactionHistory");
                    break;
                case "6": // Exit App
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
