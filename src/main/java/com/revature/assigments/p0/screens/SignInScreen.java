package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.services.AccountService;
import com.revature.assigments.p0.services.UserService;
import com.revature.assigments.p0.util.ScreenRouter;
import com.revature.assigments.p0.util.UserTracker;

import java.io.BufferedReader;
import java.io.IOException;

public class SignInScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserService userService;
    private AccountService accountService;



    public SignInScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, AccountService accountService) {
        super("SignInScreen","/signIn");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() {

        String username;
        String password;


        try{

            System.out.println("            << Sign In >>            ");
            System.out.println("-------------------------------------");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser currentUser = userService.signIn(username,password);
            if (currentUser!= null){
                System.out.println("Login successful!!");
                this.userTracker = new UserTracker(currentUser);
                this.userTracker.getUser().setAccounts(accountService.findUserAccountsByUserId(currentUser.getId()));
                router.navigate("/transaction", this.userTracker); // I'm calling the overloading method to pass the UserTracker
            }else{
                System.out.println("The email or password is incorrect.");
            }


        }catch(IOException e){

            e.printStackTrace(); // This is exception for readline method
        }


    }
}
