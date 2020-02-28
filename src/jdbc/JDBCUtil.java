import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: xzw
 * @Date: 2020/2/28
 */
public class JDBCUtil {

    private static String username = null;
    private static String password = null;
    private static String url = null;
    private static String driverClass = null;

    // 静态代码块实现数据的加载
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("jdbc\\database.properties"));
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driverClass = properties.getProperty("driverClass");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取jdbc链接
     */
    public static Connection getconn() {
        Connection connect = null;
        try {
            Class.forName(driverClass);
            connect = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connect;

    }

    /**
     * 释放链接资源
     */
    public static void release(Connection connection,Statement statement,ResultSet resultset) {
        closeConnection(connection);
        closeStatment(statement);
        closeResultSet(resultset);
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

    private static void closeStatment(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                statement = null;
            }
        }
    }

    private static void closeResultSet(ResultSet resultset) {
        if (resultset != null) {
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resultset = null;
            }
        }
    }
}
