package com.revature.assigments.p0;

import com.revature.assigments.p0.util.AppState;

/**
 * Ini class for my Canaima Bank application
 */
public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args){
        while(app.isAppRunning()){
            app.getRouter().navigate("/landing");
        }

    }

    // Method to return the app state
    public static AppState app(){
        return app;
    }

}
