package com.revature.assigments.p0.daos;

import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to handle the User related SQL statements and accesses to the DB
 */

public class UserDAO {

    public void save(AppUser newUser){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlInsertUser = "insert into p0_canaima.users(username, password, first_name, last_name, email) values (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] {"user_id"});
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getEmail());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted !=0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newUser.setId(rs.getInt("user_id"));
                }
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }


    }

    //Method to check if a value from a column is already in the database
    public boolean isUsernameAvailable(String username){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlSelectUsername = "select * from p0_canaima.users where username= ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelectUsername);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    //Method to check if a value from a column is already in the database
    public boolean isEmailAvailable(String email){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlSelectUsername = "select * from p0_canaima.users where email= ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelectUsername);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    //Method to find a user by username and password and return the user from DB
    public AppUser findUserByUsernameAndPassword(String username, String password){

        AppUser user = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlFindUser = "select * from p0_canaima.users where username = ? and password=?;";
            PreparedStatement pstmt = conn.prepareStatement(sqlFindUser);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return user;
    }


    /* Didn't not working check with the community
    public boolean isValueAvailable (String valueName, String value){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sqlSelectUsername = "select * from p0_canaima.users where ? = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelectUsername);
            pstmt.setString(1, valueName);
            pstmt.setString(2, value);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }
*/



}
