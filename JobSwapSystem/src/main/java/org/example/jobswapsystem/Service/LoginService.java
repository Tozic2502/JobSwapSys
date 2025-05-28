package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;

public class LoginService implements ILoginService {
    private final UserService userService;
    private final SessionManager sessionManager;

    public LoginService(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean login(String email, String password) {
        User user = userService.login(email, password);

        if (user != null) {
            sessionManager.setCurrentUser(user); // Store logged-in user
            return true;
        }

        return false;
    }
}



