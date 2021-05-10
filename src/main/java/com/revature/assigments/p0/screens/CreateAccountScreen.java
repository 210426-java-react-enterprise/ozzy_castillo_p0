package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.services.AccountService;
import com.revature.assigments.p0.services.UserService;
import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ScreenRouter;
import com.revature.assigments.p0.util.UserTracker;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.assigments.p0.Driver.app;

/**
 * Class to render the Create Account screen
 *
 */

public class CreateAccountScreen extends Screen{

    private BufferedReader consoleReader;
    private AccountService AccountService;
    private ScreenRouter router;


    public CreateAccountScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService,UserTracker userTracker) {
        super("TransactionScreen","/transaction",userTracker);
        this.consoleReader = consoleReader;
        this.router = router;
        this.AccountService = accountService;
    }

    @Override
    public void render() {
        /*
        ArrayList<String> accountTypes;


        try{

            // I need to bring from DB my account types


            accountTypes = AccountService

            System.out.println("       << Create an Account >>       ");
            System.out.println("-------------------------------------");



            System.out.print("");
            us = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser currentUser = userService.signIn(username,password);
            if (currentUser!= null){
                System.out.println("Login successful!!");
                this.userTracker = new UserTracker(currentUser);
                router.navigate("/transaction", this.userTracker); // I'm calling the overloading method to pass the UserTracker
            }else{
                System.out.println("The email or password is incorrect.");
            }


        }catch(IOException e){

            e.printStackTrace(); // This is exception for readline method
        }

    */
    }

}
