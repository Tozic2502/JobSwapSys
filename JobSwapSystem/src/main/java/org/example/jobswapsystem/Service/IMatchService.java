package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.User;
import java.util.List;

public interface IMatchService {
    void sendInvitation(User from, User to);
    List<User> getMatches(User user);
}
