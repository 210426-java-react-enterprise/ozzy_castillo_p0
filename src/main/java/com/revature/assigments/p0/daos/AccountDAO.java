package com.revature.assigments.p0.daos;

import com.revature.assigments.p0.models.AppAccount;
import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ConnectionFactory;
import com.revature.assigments.p0.util.UserTracker;

import java.sql.*;

/**
 * Class to handle the Account relate SQL statements and accesses to the DB
 */
public class AccountDAO {

    public ArrayList<String> findAccountTypes(){
        ArrayList<String> accountTypes = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindAccountTypes = "select acct_type from p0_canaima.account_types;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlFindAccountTypes);
            while (rs.next()){
                accountTypes.add(rs.getString("acct_type"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return accountTypes;
    }

    public int findAccountTypeId(String accountType){
        int accountId=0;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindAccountId = "select acct_tp_id from p0_canaima.account_types where acct_type = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindAccountId, new String[] {"acct_tp_id"});
            System.out.println("Account type in Account DAO is >>> "+accountType );
            pstmt.setString(1, accountType);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                accountId = rs.getInt("acct_tp_id");
                return accountId;
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return accountId;

    }

    public int findCurrencyId(String currency){
        int currencyId=0;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindAccountId = "select currency_id from p0_canaima.currencies where curr_name = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindAccountId, new String[] {"currency_id"});
            pstmt.setString(1, currency);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                currencyId = rs.getInt("currency_id");
                return currencyId;
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return currencyId;

    }

    /**
     * This method saves the new account in two steps.
     * First insert the account info into the accounts' table
     * Second insert the account with its respective user info into user_accounts table
     *
     * @param newAccount
     */
    public void save(AppAccount newAccount, UserTracker userTracker){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertAccount = "insert into p0_canaima.accounts (acct_tp_id, acct_balance, currency_id) values (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAccount, new String[] {"acct_id"});
            pstmt.setInt(1,findAccountTypeId(newAccount.getAccountType()));
            pstmt.setDouble(2, newAccount.getBalance());
            pstmt.setInt(3,findCurrencyId(newAccount.getCurrency()));

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted !=0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newAccount.setId(rs.getInt("acct_id"));
                }
                //For joint accounts I need to figure out how to iterate this insert per account holder
                String sqlInsertUserAccount = "insert into p0_canaima.user_accounts (user_id, acct_id) " +
                        "select users.user_id , accounts.acct_id " +
                        "from p0_canaima.users, p0_canaima.accounts " +
                        "where users.user_id = ? and accounts.acct_id = ?;";

                PreparedStatement pstmt2 = conn.prepareStatement(sqlInsertUserAccount);
                pstmt2.setInt(1,userTracker.getUser().getId());
                pstmt2.setInt(2, newAccount.getId());

                int rowsInserted2 = pstmt2.executeUpdate();
                if(rowsInserted2 != 0) userTracker.getUser().addAccountToUser(newAccount);
                else {
                    throw new SQLException("The user-account relation is broken >>> the system couldn't save it into the DB.!");
                }
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<AppAccount> findUserAccountsById(int userId){

        ArrayList<AppAccount> userAccounts = null;
        AppAccount account;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sqlFindUserAccounts = "select a.acct_id, at2.acct_type, a.acct_balance, c.curr_name " +
                                            "from p0_canaima.accounts a " +
                                            "left join p0_canaima.account_types at2 " +
                                            "on a.acct_tp_id = at2.acct_tp_id " +
                                            "left join p0_canaima.currencies c " +
                                            "on a.currency_id = c.currency_id " +
                                            "left join p0_canaima.user_accounts ua " +
                                            "on a.acct_id = ua.acct_id " +
                                            "where ua.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindUserAccounts);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            userAccounts = new ArrayList<>();
            int line = 0;
            while (rs.next()){
                account = new AppAccount();
                account.setId(rs.getInt("acct_id"));
                account.setAccountType(rs.getString("acct_type"));
                account.setBalance(rs.getDouble("acct_balance"));
                account.setCurrency(rs.getString("curr_name"));


                userAccounts.add(account);
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return userAccounts;
    }

    public boolean makeDeposit(int accountId, double amount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlUpdateAccountBalance = "update p0_canaima.accounts set acct_balance = acct_balance + ? where acct_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateAccountBalance);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, accountId);

            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated!=0){
                return true;
             }

            return false;

        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean makeWithdrawal(int accountId, double amount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlUpdateAccountBalance = "update p0_canaima.accounts set acct_balance = acct_balance - ? where acct_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateAccountBalance);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, accountId);

            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated!=0){
                return true;
            }

            return false;

        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }

    }


}