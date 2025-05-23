package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.Company;
import org.example.jobswapsystem.util.SqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService implements ICompanyService
{
    @Override
    public List<Company> getCompanies()
    {
        List<Company> companies = new ArrayList<Company>();
        try
        {
            Connection con = SqlConnection.getInstance();
            String sql = "{ call SP_GetCompanies() }";
            CallableStatement stmt = con.prepareCall(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Company company = new Company();
                company.setCompany_ID(rs.getInt("Company_ID"));
                company.setName(rs.getString("name"));

                companies.add(company);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Database error during register: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("We encountered an error: " + e.getMessage());
        }
        return companies;
    }
}
