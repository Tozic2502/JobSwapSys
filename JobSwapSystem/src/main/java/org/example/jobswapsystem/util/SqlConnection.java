package org.example.jobswapsystem.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Allan
public class SqlConnection
{
    private static SqlConnection instance = null;
    private static Connection con;

    private SqlConnection()
    {}

    private void init()
    {
        Properties props = new Properties();

        try (InputStream input = SqlConnection.class.getClassLoader().getResourceAsStream("db.properties"))
        {
            if (input == null)
            {
                throw new RuntimeException("db.properties not found");
            }
            props.load(input);

            String port = props.getProperty("port");
            String databaseName = props.getProperty("databaseName");
            String userName = props.getProperty("userName");
            String password = props.getProperty("password");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
            System.out.println("Database connection established.");

        }
        catch (SQLException e)
        {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    private Connection getConnection()
    {
        return con;
    }

    public static Connection getInstance()
    {
        try
        {
            if (instance == null || con == null)
            {
                instance = new SqlConnection();
                instance.init();
            }
            else if (instance.getConnection().isClosed())
            {
                instance.init();
            }
        }
        catch (Exception e)
        {
            System.err.println("Something went wrong: " + e.getMessage());
        }
        return instance.getConnection();
    }
}
