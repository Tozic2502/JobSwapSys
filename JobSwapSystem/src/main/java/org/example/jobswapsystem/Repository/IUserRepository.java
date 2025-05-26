package org.example.jobswapsystem.Repository;


import org.example.jobswapsystem.Models.User;

public interface IUserRepository {
    User getUserByEmailAndPassword(String email, String password);
    String getJobTitleByUserId(int userId);
}
