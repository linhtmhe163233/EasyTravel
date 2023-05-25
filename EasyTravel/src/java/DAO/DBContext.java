/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 21-05-2023      1.0                 DucTM           First Implement
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * This class contains the database accessing config for the web application
 * All DAO classess will be subclass of DBContext
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
    private final String password = "247314";

    public DBContext() throws Exception {

        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        conn = DriverManager.getConnection(url, userID, password);
    }

}
