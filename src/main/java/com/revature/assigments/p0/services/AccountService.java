package com.revature.assigments.p0.services;

import com.revature.assigments.p0.daos.AccountDAO;
import com.revature.assigments.p0.models.AppAccount;
import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ConnectionFactory;
import com.revature.assigments.p0.util.UserTracker;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class that contains all my business logic for ACCOUNT data validation
 */
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public ArrayList<String> findAccountTypes(){
        return accountDAO.findAccountTypes();
    }

    public void save(AppAccount newAccount, UserTracker userTracker) {

        if(isBalanceValid(newAccount.getBalance())){
            accountDAO.save(newAccount, userTracker);
        }
    }

    public boolean isBalanceValid(Double balance){
        if (balance > 0){
            return true;
        }
        return false;
    }
    public ArrayList<AppAccount> findUserAccountsByUserId(int userId){
        if( userId != 0){
            return accountDAO.findUserAccountsById(userId);
        }
        return null;
    }

    public boolean isValidDeposit(double amount){
        if (amount > 0.00) return true;
        return false;
    }

    public boolean makeDeposit(int accountId, double amount){
        return accountDAO.makeDeposit(accountId, amount);
    }

    public boolean isValidAccountNumber(int accountNumber, ArrayList<AppAccount> userAccounts ){
        for (int i = 0; i < userAccounts.size(); i++) {
            if(userAccounts.get(i).getId() == accountNumber) return true;
        }
        return false;
    }

    public boolean isValidWithdrawal(double amount, double balance){
        if(amount < 0)return false;

        if((balance-=amount) < 0 ) return false;

        return true;
    }

    public boolean makeWithdrawal(int accountId, double amount){
        return accountDAO.makeWithdrawal(accountId, amount);
    }

}
