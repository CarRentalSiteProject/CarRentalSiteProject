package fortestDBconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private String Url;
    private String User;
    private String Password;
    private String driverClassName;

    public DatabaseConnection(Properties props) throws ClassNotFoundException {
        this.Url = props.getProperty("db.url");
        this.User = props.getProperty("db.user");
        this.Password = props.getProperty("db.password");
        
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.Url, this.User, this.Password);
    }
}

