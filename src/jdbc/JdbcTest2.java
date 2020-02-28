import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用util工具类进行 数据操作测试
 *
 * （jdbc是否进行数据的提交与数据库的中的autocommit无关，只与自己链接的AutoCommit属性有关）
 * @Author: xzw
 * @Date: 2020/2/28
 */

public class JdbcTest2 {

    @Test
    public void test1() {
        /**
         * 证实：  当数据库的AutoCommit为off的时候，
         *         connection的getAutoCommit=true时
         *         JDBC在操作完sql后也会完成提交，会进行commit，让数据持久化到数据库
         */
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getconn();
            System.out.println(connection.getAutoCommit());
            statement = connection.createStatement();
            String sql = "update test1 set money = money-100 where id = 1";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("操作成功");
            } else {
                System.out.println("操作失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection, statement, null);
        }
    }


    @Test
    public void test2() {
        /**
         * 证实： 当数据库的AutoCommit为on的时候，
         *      connection的getAutoCommit=false时
         *      数据库中的值不会发生变化，
         */
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getconn();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String sql = "update test1 set money = money-100 where id = 1";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("操作成功");
            } else {
                System.out.println("操作失败");
            }
            String sql2 = "select * from test1 where id = 1";
            ResultSet resultSet = statement.executeQuery(sql2);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + "====" + resultSet.getDouble("money"));
            }

            //此时不如不进行提交，数据便不会被持久化
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection, statement, null);
        }
    }


}
