package org.example.jobswapsystem.Repository;

import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionRepository {

    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();

        try {
            Connection con = SqlConnection.getInstance();
            String sql = "{ call SP_GetPositions() }";
            CallableStatement stmt = con.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Position position = new Position();
                position.setPosition_ID(rs.getInt("Position_ID"));
                position.setJob_Title(rs.getString("Job_Title"));
                positions.add(position);
            }

        } catch (SQLException e) {
            System.err.println("Database error during select: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("We encountered an error: " + e.getMessage());
        }

        return positions;
    }
}
