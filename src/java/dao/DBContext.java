/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 21-05-2023      1.0                 DucTM           First Implement
 * 26-05-2023      1.0                 DucTM           Add close() method 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This class contains the database accessing config for the web application
 * All DAO classess will be subclass of DBContext
 * Other DAO can open and close connection to database with methods in this class
 *
 * @author DucTM
 */
public class DBContext {

    Connection conn;
    //Information of your database connection
    private final String serverName = "localhost";
    private final String dbName = "EasyTravel";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123456";

    public DBContext() throws Exception {

        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        conn = DriverManager.getConnection(url, userID, password);
    }
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
