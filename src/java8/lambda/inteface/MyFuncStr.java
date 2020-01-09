package lambda.inteface;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
@FunctionalInterface
public interface MyFuncStr {

    /**
     * 用参数字符串转化为想要的值
     */
    String getValue(String str);
}
