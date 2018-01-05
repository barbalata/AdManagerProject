package utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConf {

    private static String SetConnectionURL() {
        utils.ConfigurationFile configurationFile = new ConfigurationFile();
        return "jdbc:sqlserver://" +
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
    }

    public static String SetConnectionURL(String ServerName, Boolean InterogatedSecurity) {
        return "jdbc:sqlserver://" + ServerName + ":1433;"
                + "integratedSecurity=" + InterogatedSecurity;
    }

    public static String SetConnectionURL(String ServerName, String DatabaseName, Boolean InterogatedSecurity, String User, String Password) {
        return "jdbc:sqlserver://" + ServerName + ":1433;"
                + "databaseName=" + DatabaseName + ";"
                + "user=" + User + ";"
                + "password=" + Password + ";" + ""
                + "integratedSecurity=" + InterogatedSecurity;
    }

    public static Boolean TestConnection() {
        String connectionUrl = SetConnectionURL();
        System.out.println(connectionUrl);

        return TestConnection(connectionUrl);
    }

    public static Boolean TestConnection(String ConnectionString) {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            DriverManager.getConnection(ConnectionString);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
