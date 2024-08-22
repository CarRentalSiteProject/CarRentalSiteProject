package fortestDBconn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

public class DBC {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        InputStream input = new FileInputStream("D:/teamproject/CarRentalSiteProject/res/db.properties");
        props.load(input);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}