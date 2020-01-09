package lambda.inteface;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
@FunctionalInterface
public interface MyFunc<T, R> {

    /**
     * 计算结果
     * @param t1 参数1
     * @param t2 参数2
     * @return 结果
     */
    R getValue(T t1, T t2);
}
