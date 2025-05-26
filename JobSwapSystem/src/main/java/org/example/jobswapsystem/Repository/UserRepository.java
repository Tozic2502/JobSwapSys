package org.example.jobswapsystem.Repository;

import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection conn = SqlConnection.getInstance()) {
            String sql = "{ call SP_Login(?, ?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_ID(rs.getInt("User_ID"));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPosition_ID(rs.getInt("Position_ID"));
                user.setCompany_ID(rs.getInt("Company_ID"));
                user.setAddress_ID(rs.getInt("Address_ID"));
                user.setRole_ID(rs.getInt("Role_ID"));
                // You can add more fields here as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getJobTitleByUserId(int userId) {
        try (Connection conn = SqlConnection.getInstance()) {
            String sql = "{ call SP_GetJobTitleByUserID(?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Job_Title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}