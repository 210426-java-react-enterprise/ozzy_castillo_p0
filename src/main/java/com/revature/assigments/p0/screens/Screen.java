package com.revature.assigments.p0.screens;


import com.revature.assigments.p0.util.UserTracker;

/**
 * Abstract class to describe the behaviour of screen
 */
public abstract class Screen {

    protected String name;
    protected String route;
    protected UserTracker userTracker; // Added to keep the current user info in memory

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public Screen(String name, String route, UserTracker userTracker){
        this(name, route);
        this.userTracker = userTracker;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public UserTracker getUserTracker() {
        return userTracker;
    }

    public void setUserTracker(UserTracker userTracker) {
        this.userTracker = userTracker;
    }

    public abstract void render();

    
}
