package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;

public interface ISessionManager {
    void setCurrentUser(User user);
    User getCurrentUser();
    void clearSession();
}

