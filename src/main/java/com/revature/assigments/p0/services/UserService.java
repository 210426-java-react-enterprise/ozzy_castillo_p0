package com.revature.assigments.p0.services;

import com.revature.assigments.p0.daos.UserDAO;
import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.exceptions.invalidRequestedException;
import com.revature.assigments.p0.exceptions.ResourcesPersistanceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class that contains all my business logic for USER data validation
 */
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * This method is responsible to validate and register the user info
     * @param newUser -- user info coming from user inputs
     * @throws invalidRequestedException -- this me
     * @throws ResourcesPersistanceException
     */
    public void signUp(AppUser newUser) throws invalidRequestedException, ResourcesPersistanceException{
        //Insert my business logic validations
        if(!isUserValid(newUser)){
            throw new invalidRequestedException("Invalid new user data provided!");
        }

        if(!isEmailValid(newUser.getEmail())){
            throw new invalidRequestedException("Invalid email address provided!");
        }

        if(!userDAO.isUsernameAvailable(newUser.getUsername())){
            throw new invalidRequestedException("the username is not available please try with other username!");
        }

        if(!userDAO.isEmailAvailable(newUser.getEmail())){
            throw new invalidRequestedException("the email is not available please try with other email address!");
        }

        /*Didn't not working check with the community
        System.out.println("The email address is >>>" + newUser.getEmail());
        if(!userDAO.isValueAvailable("email", newUser.getEmail())){
            System.out.println("The email address is >>>" + newUser.getEmail());
            throw new invalidRequestedException("the email is not available please try with other email address!");
        }
        */
        userDAO.save(newUser);
    }

    public AppUser signIn(String username, String password){
        AppUser user = null;

        if (username == null || username.trim().isEmpty() || username.trim().length() > 25) {
            throw new invalidRequestedException("Invalid user data provided!");
        }

        user = userDAO.findUserByUsernameAndPassword(username, password);

        return user;
    }

    //Method to validate the data from the user with the requirements from the users' table from my DB
    public boolean isUserValid(AppUser user){
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().trim().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().trim().length() > 25) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().trim().length() > 256 )return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().trim().length() > 25) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().trim().length() > 256) return false;

        return true;
    }

    /**
     * Method to compare define email pattern against the input email address pattern
     * return true if both pattern matches
     */

    public boolean isEmailValid(String email){
        String emailPattern ="^(.+)@([^@]+[^.])$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(emailPattern);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
