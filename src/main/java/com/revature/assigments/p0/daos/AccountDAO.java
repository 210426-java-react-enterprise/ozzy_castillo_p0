package com.revature.assigments.p0.daos;

import com.revature.assigments.p0.models.AppAccount;
import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ConnectionFactory;

import java.sql.*;

/**
 * Class to handle the Account relate SQL statements and accesses to the DB
 */
public class AccountDAO {

    public ArrayList<String> getAccountTypes(){
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

    public int getAccountTypeId(String accountType){
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

    public int getCurrencyId(String currency){
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
    public void save(AppAccount newAccount, int userId){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertAccount = "insert into p0_canaima.accounts (acct_tp_id, acct_balance, currency_id) values (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAccount, new String[] {"acct_id"});
            pstmt.setInt(1,getAccountTypeId(newAccount.getAccountType()));
            pstmt.setDouble(2, newAccount.getBalance());
            pstmt.setInt(3,getCurrencyId(newAccount.getCurrency()));

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted !=0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newAccount.setId(rs.getInt("acct_id"));
                    String sqlInsertUserAccount = "insert into p0_canaima.user_accounts (user_id, acct_id) " +
                                                    "select users.user_id , accounts.acct_id " +
                                                    "from p0_canaima.users, p0_canaima.accounts " +
                                                    "where users.user_id = ? and accounts.acct_id = ?;";
                    pstmt.setInt(1,userId);
                    pstmt.setInt(2, newAccount.getId());

                    int rowsInserted2 = pstmt.executeUpdate();
                    if(rowsInserted2 == 0){
                        throw new SQLException("The user-account relation is broken >>> the system couldn't save it into the DB.!");
                    }

                }
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
