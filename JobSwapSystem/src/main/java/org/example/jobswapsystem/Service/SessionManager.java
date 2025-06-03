package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;
//Mikkel
public class SessionManager implements ISessionManager {
    private User currentUser;

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void clearSession() {
        this.currentUser = null;
    }
}
