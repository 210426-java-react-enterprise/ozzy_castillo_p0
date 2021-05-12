package com.revature.assigments.p0.screens;

import com.revature.assigments.p0.exceptions.invalidRequestedException;
import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.services.UserService;
import com.revature.assigments.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class to render the Sign Up screen
 */

public class SignUpScreen extends Screen{


    private BufferedReader consoleReader;
    private UserService userService;

    public SignUpScreen(BufferedReader consoleReader, UserService userService) {
        super("SignUpScreen","/signUp");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    @Override
    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;

        try{
            System.out.println("          << Sign up now >>          ");
            System.out.println("-------------------------------------");

            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser newUser = new AppUser(username,password,firstName,lastName,email);

            userService.signUp(newUser);


        }catch(invalidRequestedException e){
            System.out.println(e.getMessage()); // This is exception for readline method
            System.out.print("Would you like to try again?");
            System.out.printf("(yes or no) >> ");
            try {
                String userConfirm = consoleReader.readLine();
                if ( userConfirm.trim().toLowerCase().equals("yes")){
                    this.render();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }catch(IOException e){
           e.printStackTrace();

        }



    }
}
