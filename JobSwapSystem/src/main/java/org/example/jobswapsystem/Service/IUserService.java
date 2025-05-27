package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.Address;
import org.example.jobswapsystem.Models.User;

public interface IUserService
{
    User login(String email, String password);
    void register(User user, Address address);
    User UpdateUser(User user);

}
