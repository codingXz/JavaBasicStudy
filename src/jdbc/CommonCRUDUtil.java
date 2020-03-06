/**
 * 通用的crud数据库支持类
 *
 * 模仿apache.DBUtil完成
 * DBUtil使用：new QueryRunner(DateSource)；queryRunner.update()/query()
 * DateSource：是一个连接池对象
 *
 * @Author: xzw
 * @Date: 2020/3/6
 */
public class CommonCRUDUtil {

    public static void query() {
        //1.活得参数的个数
        //2.对参数进行赋值
        //3.结果集的处理 ：解决输出结果类型、以及数据封装的问题（通过接口和泛型来实现）

    }

    public static void update(String sql, Object... args) {

    }
}
