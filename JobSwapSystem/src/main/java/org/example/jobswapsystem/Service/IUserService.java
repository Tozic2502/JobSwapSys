package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;

public interface IUserService
{
    User login(String email, String password);
    void register(User user);
    void getJobTitleByUserId(int userId);
}
