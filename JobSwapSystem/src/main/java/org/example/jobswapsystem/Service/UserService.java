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
                        //user.setCompanyName(getCompanyName(conn, user.getCompanyID()));
                        //user.setJobTitle(getPositionTitle(conn, user.getPositionID()));
                        //user.setLocation(getCity(conn, user.getAddressID()));

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during login: " + e.getMessage());
        }

        return null; // Login failed
    }
    private String getCompanyName(Connection conn, int companyId) throws SQLException {
        String sql = "{ call SP_GetCompanyName(?) }"; // Replace with your actual SP
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, companyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Company_Name"); // Adjust column name as per your SP
                }
            }
        }
        return "Unknown Company";
    }

    private String getPositionTitle(Connection conn, int positionId) throws SQLException {
        String sql = "{ call SP_GetPositionTitle(?) }"; // Replace with your actual SP
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, positionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Title"); // Adjust column name as per your SP
                }
            }
        }
        return "Unknown Position";
    }

    private String getCity(Connection conn, int addressId) throws SQLException {
        String sql = "{ call SP_GetCity(?) }"; // Replace with your actual SP
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, addressId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("City"); // Adjust column name as per your SP
                }
            }
        }
        return "Unknown City";
    }




}
