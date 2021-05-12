package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.services.AccountService;
import com.revature.assigments.p0.util.ScreenRouter;
import com.revature.assigments.p0.util.UserTracker;

import java.io.BufferedReader;
import java.io.IOException;

public class WithdrawalScreen extends Screen{
    private BufferedReader consoleReader;
    private com.revature.assigments.p0.services.AccountService AccountService;
    private ScreenRouter router;

    public WithdrawalScreen(BufferedReader consoleReader, ScreenRouter router, com.revature.assigments.p0.services.AccountService accountService, UserTracker userTracker) {
        super("Withdrawal","/withdrawal", userTracker);
        this.consoleReader = consoleReader;
        AccountService = accountService;
        this.router = router;
    }

    @Override
    public void render() {

        System.out.println("            << Withdrawl >>            ");
        System.out.println("-------------------------------------");
        if (userTracker.getUser().getAccounts().size() > 1){
            System.out.println("From which account would you like to withdrawal? ");
            System.out.println("--------------------------------------------");
            for (int i = 0; i <  userTracker.getUser().getAccounts().size(); i++){
                System.out.format("%d.-Account# %d Type: %s Your Current Balance is $ %.2f%n\n",
                        i+1,
                        userTracker.getUser().getAccounts().get(i).getId(),
                        userTracker.getUser().getAccounts().get(i).getAccountType(),
                        userTracker.getUser().getAccounts().get(i).getBalance());
            }
            try{
                System.out.print(">> ");
                String accountUserSelection = consoleReader.readLine();
                if(!AccountService.isValidAccountNumber(Integer.parseInt(accountUserSelection), userTracker.getUser().getAccounts())){
                    throw new IllegalArgumentException("Your selection is invalid!");
                }
                System.out.println("How much would you like to withdrawal? ");
                System.out.print(">> ");
                String amountToWithdrawal = consoleReader.readLine();
                System.out.println("Please confirm the operation");
                //System.out.printf("Are you planning to deposit $ %.2f%n into your Account # %s Type: %s", Double.parseDouble(amountToDeposit), accountUserSelection, userTracker.getUser().getAccounts().get(Integer.parseInt(accountUserSelection)).getAccountType()+"\n");

                // I can improve this - I need to add this to my ArrayList in someway
                int accountIndex = -1;
                for (int i = 0; i <  userTracker.getUser().getAccounts().size(); i++){
                    if (userTracker.getUser().getAccounts().get(i).getId() == Integer.parseInt(accountUserSelection)){
                        accountIndex = i;
                        break;
                    }

                }

                System.out.printf("Are you planning to withdrawal $ %.2f%n from your Account # %s Type: %s",
                        Double.parseDouble(amountToWithdrawal), accountUserSelection,
                        userTracker.getUser().getAccounts().get(accountIndex).getAccountType()+"\n");
                System.out.printf("(yes or no) >> ");
                String userConfirm = consoleReader.readLine();
                if ( userConfirm.trim().toLowerCase().equals("yes")){
                    if(!AccountService.isValidWithdrawal(Double.parseDouble(amountToWithdrawal), userTracker.getUser().getAccounts().get(accountIndex).getBalance())){
                        throw new IllegalArgumentException("Your withdrawal is invalid!");
                    }
                    /*
                    if(AccountService.makeDeposit(userTracker.getUser().getAccounts().get(Integer.parseInt(accountUserSelection)-1).getId(),Double.parseDouble(amountToDeposit))){
                        userTracker.getUser().getAccounts().get(Integer.parseInt(accountUserSelection)).addToBalance(Double.parseDouble(amountToDeposit));
                        System.out.println("Your deposit was completed successfully!");
                    }
                     */
                    if(AccountService.makeWithdrawal(userTracker.getUser().getAccounts().get(accountIndex).getId(),Double.parseDouble(amountToWithdrawal)) && accountIndex != -1){

                        userTracker.getUser().getAccounts().get(accountIndex).subtractToBalance(Double.parseDouble(amountToWithdrawal));
                        System.out.println("Your withdrawal was completed successfully!");
                    }else{
                        throw new IllegalArgumentException("Invalid Selection!");
                    }
                }else{
                    throw new IllegalArgumentException("Invalid Selection!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(">>>>> Your selection is invalid, please use the Account #");
                this.render();
                e.printStackTrace();
            } catch (IOException e2){
                e2.printStackTrace();
            }
        }else{
            System.out.println("Please create an account to be able to withdrawal");
            System.out.println("-----------------------------------------------");
        }



        router.navigate("/transaction", this.userTracker);
    }
}
