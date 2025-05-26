package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Repository.UserReposistory;

    public class LoginService {

        private final UserReposistory userReposistory;

        public LoginService(UserReposistory userRepository) {
            this.userReposistory = userRepository;
        }

        /**
         * Attempts to log in a user with the provided email and password.
         * @param email The user's email address.
         * @param password The user's password.
         * @return A User object if login is successful; null otherwise.
         */
        public User login(String email, String password) {
            return userReposistory.authenticate(email, password);
        }
    }

