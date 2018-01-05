package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConf {
    //Declare JDBC Driver
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //Connection
    private static Connection connection = null;

    //Connection String
    //String connStr = "jdbc:sqlserver://ServerName:1433;databaseName=DatabaseNameValue;user=UserNameValue;password=PasswordValue;IntegratedSecurity=IntegratedSecurityValue";
    private static String connectionString = null;

    private static String SetConnectionURL() {
        utils.ConfigurationFile configurationFile = new ConfigurationFile();
        connectionString = "jdbc:sqlserver://" +
                configurationFile.getServer() +
                ":1433;" +
                "databaseName=" +
                configurationFile.getDatabase() +
                ";user=" +
                configurationFile.getUser() +
                ";password=" +
                configurationFile.getPassword() +
                ";integratedSecurity=" +
                configurationFile.getIntegratedSecurity();
        return connectionString;
    }

    public static String SetConnectionURL(String ServerName, Boolean InterogatedSecurity) {
        connectionString = "jdbc:sqlserver://" + ServerName + ":1433;"
                + "integratedSecurity=" + InterogatedSecurity;
        return connectionString;
    }

    public static String SetConnectionURL(String ServerName, String DatabaseName, Boolean InterogatedSecurity, String User, String Password) {
        connectionString = "jdbc:sqlserver://" + ServerName + ":1433;"
                + "databaseName=" + DatabaseName + ";"
                + "user=" + User + ";"
                + "password=" + Password + ";" + ""
                + "integratedSecurity=" + InterogatedSecurity;
        return connectionString;
    }

    public static Boolean TestConnection() {
        String connectionUrl = SetConnectionURL();
        System.out.println(connectionUrl);

        return TestConnection(connectionUrl);
    }

    public static Boolean TestConnection(String ConnectionString) {
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            DriverManager.getConnection(ConnectionString);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting Oracle JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Oracle JDBC Driver Registered!");

        //Establish the Oracle Connection using Connection String
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
