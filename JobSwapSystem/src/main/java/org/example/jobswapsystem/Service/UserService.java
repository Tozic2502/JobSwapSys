package org.example.jobswapsystem.Service;
import org.example.jobswapsystem.Models.Address;
import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//Mikkel
public class UserService implements IUserService
{

    public UserService() {

    }

    public User login(String email, String password)
    {
        try (Connection conn = SqlConnection.getInstance()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection");
                return null;
            }

            String sql = "{ call SP_Login(?, ?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setUser_ID(rs.getInt("User_ID"));
                        user.setName(rs.getString("Name"));
                        user.setEmail(rs.getString("Email"));
                        user.setCompany_ID(rs.getInt("Company_ID"));
                        user.setRole_ID(rs.getInt("Role_ID"));
                        user.setAddress_ID(rs.getInt("Address_ID"));
                        user.setPosition_ID(rs.getInt("Position_ID"));


                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during login: " + e.getMessage());
        }

        return null; // Login failed
    }

    //Allan

    /**
     * takes user object and address object to create a new user entry in database and address
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

    public void getJobTitleByUserId(int userId) {
        String jobTitle = "Unknown";

        try (Connection conn = SqlConnection.getInstance()) {
            String sql = "{ call SP_GetJobTitleByUser(?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        Position position = new Position();
                        position.setJob_Title(rs.getString("Job_Title"));
                        user.setPosition(position);
                        jobTitle = user.getPosition().getJob_Title();
                        System.out.println(jobTitle);

                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to get job title: " + e.getMessage());
        }

    }

}
