package org.example.jobswapsystem.Service;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        user.setUserID(rs.getInt("User_ID"));
                        user.setName(rs.getString("Name"));
                        user.setEmail(rs.getString("Email"));
                        user.setCompanyID(rs.getInt("Company_ID"));
                        user.setRoleID(rs.getInt("Role_ID"));
                        user.setAddressID(rs.getInt("Address_ID"));
                        user.setPositionID(rs.getInt("Position_ID"));


                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during login: " + e.getMessage());
        }

        return null; // Login failed
    }

    public User getUserDetails(int userID) throws SQLException {
        try (Connection conn = SqlConnection.getInstance()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection");
                return null;
            }

            String sql = "{ call GetUserDetails(?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, userID);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setLocation(rs.getString("City"));
                        user.setJobTitle(rs.getString("Job_Title"));
                        user.setCompanyName(rs.getString("Name"));

                        return user;
                    }
                }

            } catch (SQLException e) {
                System.err.println("Database error during login: " + e.getMessage());
            }


            return null;
        }
    }




}
