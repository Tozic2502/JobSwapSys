package org.example.jobswapsystem.Repository;

import org.example.jobswapsystem.Models.*;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    //Mikkel og Allan
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

    /**
     * Gets all job swap request entries from the database by user id.
     * if the current user is the one receiving the request it will get it but,
     * if the current user was the one sending the request it will only receive those
     * where the receiving user has accepted the request
     * @param loggedInUser
     * @return
     */
    private void getUserSwapRequests(User loggedInUser)
    {
        List<Swap_Req> swap_reqs = new ArrayList<>();
        try
        {
            Connection con = SqlConnection.getInstance();
            String sql = "{ call SP_Get_Swap_Req(?) }";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setInt(1, loggedInUser.getUser_ID());

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Swap_Req swap_req = new Swap_Req();
                swap_req.setUser1_ID(rs.getInt("User1_ID"));
                swap_req.setUser2_ID(rs.getInt("User2_ID"));
                swap_req.setUser2_Accept(rs.getBoolean("User2_Accept"));
                if (loggedInUser.getUser_ID() == swap_req.getUser1_ID())
                {
                    swap_req.setUser1(loggedInUser);
                }
                else
                {
                    swap_req.setUser2(loggedInUser);
                }
                swap_reqs.add(swap_req);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error retrieving user swap requests " + e.getMessage());
        }
        finally
        {
            loggedInUser.setSwap_req(swap_reqs);
        }
    }

    /**
     * Retrieves and populates additional details for a given user from the database.
     *
     * <p>This method calls the stored procedure {@code GetUserDetails} using the provided user's ID,
     * and populates the user's {@link Position}, {@link Company}, and {@link Address} fields with
     * values retrieved from the result set.</p>
     *
     * @param loggedInUser The {@link User} object for which additional details are to be fetched.
     *                     This object should contain at least the user's ID.
     * @return The updated {@link User} object with additional details (job title, company name,
     *         and city) populated if the user is found; otherwise returns the original object.
     */
    //Sebastian
   @Override
    public User getUserDetails(User loggedInUser) {
        try (Connection conn = SqlConnection.getInstance()) {
            String sql = "{ call GetUserDetails(?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, loggedInUser.getUser_ID());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Initialize nested models if they are null
                        if (loggedInUser.getPosition() == null)
                        {
                            loggedInUser.setPosition(new Position());
                        }

                        loggedInUser.getPosition().setJob_Title(rs.getString("Job_Title"));

                        if (loggedInUser.getCompany() == null)
                        {
                            loggedInUser.setCompany(new Company());
                        }
                        loggedInUser.getCompany().setName(rs.getString("Company"));

                        if (loggedInUser.getAddress() == null)
                        {
                            loggedInUser.setAddress(new Address());
                        }
                        loggedInUser.getAddress().setCity(rs.getString("City"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user details: " + e.getMessage());
        }

        getUserSwapRequests(loggedInUser);

        return loggedInUser;
    }
}