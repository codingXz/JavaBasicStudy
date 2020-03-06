import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用jdbc来演示事务
 *
 * connection.setAutoCommit()只会针对某一个连接生效
 *
 * 事务的隔离级别：...
 * read uncommitted
 * read committed
 * repeatable read
 * serializable
 *
 * @Author: xzw
 * @Date: 2020/2/28
 */
public class TransactionDemo {

    /**
     * 演示id为2的人 给 1  转账100元
     *
     * 测试事务正常提交的情况
     */
    @Test
    public void test1() throws SQLException {
        Connection connection = JDBCUtil.getconn();
        connection.setAutoCommit(false);
        String sql1 = "update test1 set money = money+100 where id= 1";
        String sql2 = "update test1 set money = money-100 where id= 2";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        try {
            int a1 = ps1.executeUpdate();
            int a2 = ps2.executeUpdate();
            if (a1 > 0 && a2 > 0) {
                connection.commit();
                System.out.println("转账成功");
            }
        } catch (Exception e) {
            connection.rollback();
        }

    }

    /**
     * 测试事务回滚的情况
     */
    @Test
    public void test2() throws SQLException {
        Connection connection = JDBCUtil.getconn();
        connection.setAutoCommit(false);
        String sql1 = "update test1 set money = money+100 where id= 1";
        String sql2 = "update test1 set money = money-100 where id= 2";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        try {
            int a1 = ps1.executeUpdate();
            String str = null;
            str.length();
            int a2 = ps2.executeUpdate();
            if (a1 > 0 && a2 > 0) {
                connection.commit();
                System.out.println("转账成功");
            }
        } catch (Exception e) {
            connection.rollback();
            System.out.println("转账失败，事务回滚");
        }
    }
}
