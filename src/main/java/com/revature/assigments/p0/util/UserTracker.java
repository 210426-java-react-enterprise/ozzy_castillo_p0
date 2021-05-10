package com.revature.assigments.p0.util;

import com.revature.assigments.p0.models.AppUser;

/**
 * Class to keep in memory current user information and transactions
 */
public class UserTracker {


    private AppUser user;

    public UserTracker(AppUser user){
        this.user = user;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
