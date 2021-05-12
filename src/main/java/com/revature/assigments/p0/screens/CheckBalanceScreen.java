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

        if ( userTracker.getUser().getAccounts().size() > 0){
            System.out.println("     << Check Balance Account >>     ");
            System.out.println("-------------------------------------");
            if ( userTracker.getUser().getAccounts().size() > 1){
                System.out.println("Here are the balances of your accounts:");
                System.out.println("-------------------------------------");
            }else{
                System.out.println("Here is the balance of your account:");
                System.out.println("-------------------------------------");
            }

            for (int i = 0; i <  userTracker.getUser().getAccounts().size(); i++){
                System.out.format("%d.-Account# %d Type: %s Your Balance is $ %.2f%n\n",
                        i+1,
                        userTracker.getUser().getAccounts().get(i).getId(),
                        userTracker.getUser().getAccounts().get(i).getAccountType(),
                        userTracker.getUser().getAccounts().get(i).getBalance());
            }
        }else{
            System.out.println(">>>>> Please create an account, you don't have an account to check!");
        }


        router.navigate("/transaction", this.userTracker);
    }
}
