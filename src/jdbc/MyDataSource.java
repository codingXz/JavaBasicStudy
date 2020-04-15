import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 自定义连接池（最简单写法）
 * 使用链接池就是为了减少connection对应的反复创建和销毁对资源进行的浪费
 *
 * @Author: xzw
 * @Date: 2020/3/30
 */
public class MyDataSource implements DataSource {

    private static String userName = "root";

    private static String password = "123123";

    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/wl-xzw?useUnicode=true&serverTimezone=UTC&characterEncoding=UTF-8";


    /**
     * 存放数据库连接对象的容器
     */
    private static List<Connection> connectList = new ArrayList<>(10);

    private MyDataSource() {
    }


    public static MyDataSource getInstance() {
        return DataSourceBuilder.instance;
    }

    /**
     * 静态内部类实现的单例模式，在第一次调用时进行实例化
     */
    private static class DataSourceBuilder{
        private static MyDataSource instance = new MyDataSource();
    }

    /**
     * 容器的实例化
     * 数据库的对应信息到配置文件中进行读取
     */
    private static void init() {
        System.out.println("MyDataSource 初始化了");

        for (int i = 0; i < 10; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbUrl, userName, password);
            } catch (SQLException e) {
                System.out.println("数据库连接存在问题");
                e.printStackTrace();
            }
            connectList.add(connection);
        }
    }

    static {
        init();
    }


    @Override
    public Connection getConnection() throws SQLException {
        if (connectList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                Connection connection = DriverManager.getConnection(dbUrl, userName, password);
                connectList.add(connection);
            }
        }
        // 返回第一个对象，并且从集合中移除
        return connectList.remove(0);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
