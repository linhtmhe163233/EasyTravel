    /*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 21-05-2023      1.0                 DucTM           First Implement
 * 26-05-2023      1.0                 DucTM           Add close() method 
 * 06-06-2023      1.0                 DucTM           Add getConnection() for opening a new connection
 */
package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * This class contains the database accessing config for the web application
 * All DAO classess will be subclass of DBContext
 * Other DAO can open and close connection to database with methods in this class
 *
 * @author DucTM
 */
public class DBContext {

    //Information of your database connection
    private final String serverName = "localhost";
    private final String dbName = "EasyTravel";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "03102002";

    public DBContext(){   
    }
    
    //Open a new database connection
    public Connection getConnection() throws Exception{
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Database open connection error");
        }

        return DriverManager.getConnection(url, userID, password);
    }
    //Close the current database connection
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception{
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new Exception("Database close connection error");
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new Exception("Database close connection error");
            }
        }

        try {
            if (conn != null && !conn.isClosed()) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                    conn.setAutoCommit(true);
                }
                conn.close();
            }
        } catch (SQLException e) {
            throw new Exception("Database close connection error");
        }
    }
}
