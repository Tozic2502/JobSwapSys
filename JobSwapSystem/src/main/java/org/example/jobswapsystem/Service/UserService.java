package org.example.jobswapsystem.Service;
import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//Mikkel
public class UserService {

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
