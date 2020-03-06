import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 演示PrepareStatement 和 statement的区别
 *
 * 1.statement
 *  是执行sql一定需要使用到的对象，是prepareStatement的父类
 *  String sql = "insert test1(name,money) value(" + name + ","+money+")";
 *  resultSet = execute(sql)，
 *  存在问题:因为sql的参数是直接用字符串拼接的方式来完成的，
 *  a.使用不方便
 *  b.如果在参数中传递sql关键字，会引起sql注入
 *
 *
 * 2.PrepareStatement
 *  可以防止sql注入，sql中所有的参数都是用占位符进行代替，在对象创建时会对sql进行解析，
 *  后面传递的参数不论是否包含sql的关键字都会按照字符进行处理。所以可以解决到sql注入的问题
 *  sql = "insert test1(name,money) value(?,?)"; execute(sql)
 *  占位符的赋值：从1开始
 *
 * @Author: xzw
 * @Date: 2020/2/28
 */
public class PrepareStatementDemo {

    /**
     * 使用statement完成数据的插入
     */
    @Test
    public void testStatement() throws SQLException {
        Connection connection = JDBCUtil.getconn();
        Statement statement = connection.createStatement();
        String name = "xiaohei";
        int money = 1000;
        String sql = "insert test1(name,money) value(" + name + ","+money+")";
        int i = statement.executeUpdate(sql);
        if (i > 0) {
            System.out.println("更新成功！");
        }
    }

    /**
     * 使用prepareStatement完成数据的插入
     */
    @Test
    public void testPrepareStatement() throws SQLException {
        Connection connection = JDBCUtil.getconn();
        String sql = "insert test1(name,money) value(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        // 占位符的匹配索引从1开始
        ps.setString(1,"zty");
        ps.setInt(2, 2000);
        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("更新成功！");
        }
    }
}
