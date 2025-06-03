package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;
//Mikkel og Allan
public class LoginService implements ILoginService {
    private final UserService userService;
    private final ISessionManager sessionManager;

    public LoginService(UserService userService, ISessionManager sessionManager) {
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



