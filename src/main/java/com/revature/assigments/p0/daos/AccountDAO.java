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

    public int getAccountId(String accountType){
        int accountId=0;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindAccountId = "select acct_tp_id from p0_canaima.account_types where acct_type = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindAccountId, new String[] {"acct_tp_id"});
            pstmt.setString(1, accountType);
            ResultSet rs = pstmt.executeQuery(sqlFindAccountId);
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
            String sqlFindAccountId = "select acct_tp_id from p0_canaima.currencies where acct_type = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindAccountId, new String[] {"currency_id"});
            pstmt.setString(1, currency);
            ResultSet rs = pstmt.executeQuery(sqlFindAccountId);
            while(rs.next()){
                currencyId = rs.getInt("currency_id");
                return currencyId;
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return currencyId;

    }

    public void save(AppAccount newAccount){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertUser = "insert into p0_canaima.accounts (acct_tp_id, acct_balance, currency_id) values (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] {"acct_id"});
            pstmt.setString(1, newAccount.getAccountType());
            pstmt.setString(2, Double.toString(newAccount.getBalance()));
            pstmt.setString(3, newAccount.getCurrency());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted !=0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newAccount.setId(rs.getInt("acct_id"));
                }
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
