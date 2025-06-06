package org.example.jobswapsystem.Service;
import org.example.jobswapsystem.Models.Address;
import org.example.jobswapsystem.Models.Company;
import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Repository.IUserRepository;
import org.example.jobswapsystem.Repository.UserRepository;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IUserService
{
    private final IUserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    public User getUserdDetails(User loggedInUser) {
        return userRepository.getUserDetails(loggedInUser);
    }

    //Allan
    /**
     * takes user object and address object to create a new user entry in database and address
     *
     * @param user
     * @param address
     */
    @Override
    public void register(User user, Address address)
    {
        try
        {
            Connection conn = SqlConnection.getInstance();
            String sql = "{ call SP_Register(?, ?, ?, ?, ?, ?, ?, ?) }";

            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getCompany_ID());
            stmt.setInt(5, user.getPosition_ID());
            stmt.setString(6, address.getPotalCode());
            stmt.setString(7, address.getAddress());
            stmt.setString(8, address.getCity());

            stmt.execute();
        }
        catch (SQLException e)
        {
            System.err.println("Database error during register: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("We encountered an error: " + e.getMessage());
        }
    }

    //Allan
    /**
     * Takes user object witch within has the address to update user info with the new data
     *
     * @param user
     * @return
     */
    @Override
    public User UpdateUser(User user)
    {
        try
        {
            Connection conn = SqlConnection.getInstance();
            String sql = "{ call SP_UpdateUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, user.getUser_ID());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getAddress_ID());
            stmt.setInt(6, user.getCompany_ID());
            stmt.setInt(7, user.getRole_ID());
            stmt.setInt(8, user.getPosition_ID());
            stmt.setString(9, user.getAddress().getPotalCode());
            stmt.setString(10, user.getAddress().getAddress());
            stmt.setString(11, user.getAddress().getCity());

            stmt.execute();
        }
        catch (SQLException e)
        {
            System.err.println("Database error during update: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("We encountered an error: " + e.getMessage());
        }
        return user;
    }
}


