package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.models.AppAccount;
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

        ArrayList<String> accountTypes;
        String accountType;
        double balance;


        try{

            // I need to bring from DB my account types

            System.out.println("       << Create an Account >>       ");
            System.out.println("-------------------------------------");
            System.out.println("What type account do you want to create?");
            accountTypes = AccountService.findAccountTypes();
            for (int i = 0; i < accountTypes.size(); i++) {
                System.out.printf("%d.-%s \n", i+1, accountTypes.get(i));
            }

            accountType = consoleReader.readLine();

            if ( accountType !=null && Integer.parseInt(accountType) <= AccountService.findAccountTypes().size()){

                accountType = accountTypes.get((Integer.parseInt(accountType))-1);

                try{
                    System.out.print("How much would you like to deposit?: ");
                    balance = Double.parseDouble(consoleReader.readLine());
                    AppAccount newAccount = new AppAccount(accountType,"USD",balance);
                    //AccountService.save(newAccount, userTracker.getUser().getId());
                    AccountService.save(newAccount, userTracker);
                    //I need to update userTracker.user.Accounts
                    System.out.println("The account was saved successfully.");

                }catch (NumberFormatException e){
                    System.out.print("Invalid input please try again!");
                }

            }else{
                System.out.println("Invalid Selection!");
            }

        }catch(Exception e){

            e.printStackTrace(); // This is exception for readLine method
        }


    }

}
