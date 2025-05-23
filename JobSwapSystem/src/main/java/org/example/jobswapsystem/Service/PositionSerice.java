package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionSerice implements IPositionService
{
    @Override
    public List<Position> getPositions()
    {
        List<Position> positions = new ArrayList<>();

        try
        {
            Connection con = SqlConnection.getInstance();
            String sql = "{ call SP_GetPositions() }";
            CallableStatement stmt = con.prepareCall(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Position position = new Position();
                position.setPosition_ID(rs.getInt("Position_ID"));
                position.setJob_Title(rs.getString("Job_Title"));

                positions.add(position);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Database error during select: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("We encountered an error: " + e.getMessage());
        }
        return positions;
    }
}
