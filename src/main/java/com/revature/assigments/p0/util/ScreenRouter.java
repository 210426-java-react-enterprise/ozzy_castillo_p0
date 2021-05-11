package com.revature.assigments.p0.util;

import com.revature.assigments.p0.models.AppUser;
import com.revature.assigments.p0.screens.Screen;

/**
 * Class to handle the navigation between my app's screens using my ArrayList implementation
 */

public class ScreenRouter {
    private ArrayList<Screen> screens = new ArrayList<>();

    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }

    public void navigate(String route){
        //int arraySize = screens.size() - 1;
        int arraySize = screens.size();
        for (int i = 0; i < arraySize ; i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)){ // Obj1 > String from getRoute --- Obj2 > String from route and getting a boolean this methods belongs to Object Class
                screen.render();
            }

        }
    }

    //Overloading to update the UserTracker
    public void navigate(String route, UserTracker userTracker){
        int arraySize = screens.size();
        for (int i = 0; i < arraySize ; i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)){ // Obj1 > String from getRoute --- Obj2 > String from route and getting a boolean this methods belongs to Object Class
                screen.setUserTracker(userTracker);
                screen.render();
            }

        }
    }


}
