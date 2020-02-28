import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: xzw
 * @Date: 2020/2/28
 */
public class JdbcTest1 {

    public static void main(String[] args){

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1.注册驱动
            // 在new com.mysql.jdbc.Driver()中有静态代码块 static{ DriverManager.registerDriver()}，只要这个类被加载了，就会马上注册这个驱动
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver()); ===替换===>
            Class.forName("com.mysql.jdbc.Driver");

            // 2.建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wl-xzw?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8", "root", "123123");

            // 3.创建Statement 操作数据库一定需要使用到这个对象
            /**
             * 注意当statement执行sql时，不能识别外部参数传入的sql关键字，就会出现sql注入的问题
             *
             * 所以出现了 PreparedStatement 对sql进行预先处理
             * 后面进行两者区别的演示
             */
            statement = connection.createStatement();
            String sql = "select * from sys_role";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String roleName = resultSet.getString("roleName");
                String remark = resultSet.getString("remark");
                System.out.println(id + "==" + roleName + "==" + remark);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }

    }
}
