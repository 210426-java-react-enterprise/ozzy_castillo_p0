package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.util.ScreenRouter;
import java.io.BufferedReader;
import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.util.UserTracker;

import static com.revature.assigments.p0.Driver.app;


public class TransactionScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;



    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router, UserTracker userTracker) {
        super("TransactionScreen","/transaction",userTracker);
        this.consoleReader = consoleReader;
        this.router = router;
    }


    @Override
    public void render() {

        System.out.println("-------------------------------------");
        System.out.println("            CANAIMA BANK"             );
        System.out.println("-------------------------------------");
        System.out.println("1.-Create Account");
        System.out.println("2.-Check Balance");
        System.out.println("3.-Deposit");
        System.out.println("4.-Withdraw");
        //System.out.println("5.-Transfer");
        //System.out.println("6.-Transactions history");
        System.out.println("7.-Exit Application");


        try{
            System.out.printf(">> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1": //Create Account
                    router.navigate("createAccount", this.userTracker);
                    break;
                case "2": //Check Balance
                    router.navigate("/checkBalance", this.userTracker);
                    break;
                case "3": //Deposit
                    router.navigate("/deposit", this.userTracker);
                    break;
                case "4": //Withdraw
                    router.navigate("/withdrawal", this.userTracker);
                    break;
                /*
                    case "5": //Transfer
                    router.navigate("/transfer");
                    break;
                case "6": //Transaction history
                    router.navigate("/transactionHistory");
                    break;
                */
                case "7": // Exit App
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
