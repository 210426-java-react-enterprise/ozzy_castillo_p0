package com.revature.assigments.p0.services;

import com.revature.assigments.p0.daos.AccountDAO;
import com.revature.assigments.p0.models.AppAccount;
import com.revature.assigments.p0.util.ArrayList;

/**
 * Class that contains all my business logic for ACCOUNT data validation
 */
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public ArrayList<String> getAccountTypes(){
        return accountDAO.getAccountTypes();
    }

    public void save(AppAccount newAccount, int userId){

        if(isBalanceValid(newAccount.getBalance())){
            accountDAO.save(newAccount, userId);
        }
    }

    public boolean isBalanceValid(Double balance){
        if (balance > 0){
            return true;
        }
        return false;
    }


}
