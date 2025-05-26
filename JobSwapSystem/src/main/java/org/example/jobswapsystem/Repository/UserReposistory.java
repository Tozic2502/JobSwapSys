package org.example.jobswapsystem.Repository;

import org.example.jobswapsystem.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserReposistory {
    public User getUserByEmailAndPassword(Connection conn, String email, String password) throws SQLException {
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
        return null;
    }

    public User getUserById(Connection conn, int userId) throws SQLException {
        String sql = "{ call SP_GetUserById(?) }";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, userId);
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
        return null;
    }
}
