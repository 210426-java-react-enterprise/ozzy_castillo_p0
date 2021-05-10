package com.revature.assigments.p0.daos;

import com.revature.assigments.p0.util.ArrayList;
import com.revature.assigments.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to handle the Account relate SQL statements and accesses to the DB
 */
public class AccountDAO {

    public ArrayList<String> getAccountTypes(){
        ArrayList<String> accountTypes = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindAccountTypes = "select p0_canaima.acct_type from account_types;";
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

}
