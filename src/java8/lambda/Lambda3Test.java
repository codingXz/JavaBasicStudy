package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 *
 * Java8 内置的四个核心函数式接口
 *
 * 一、Consumer<T> ： 消费型接口
 *      方法:  void accept(T t)
 *
 * 二、supplier<T> ：供给型接口
 *      方法: T get()
 *
 * 三、Function<T,R> ：函数型接口
 *      方法：R apply(T t)
 *
 * 四、Predicate<T> ： 断言型接口
 *      方法：boolean test(T t)
 *
 * @Author: xzw
 */
public class Lambda3Test {

    /**
     *     Consumer<T> 示例
     */
    @Test
    public void test1() {
        method1(1000, (money) -> System.out.println("xzw上班每天工资" + money + "元"));
    }

    private void method1(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    /**
     * supplier<T> 示例
     * 生成一个集合 用随机数
     */
    @Test
    public void test2() {
        supplierNums(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }

    private List<Integer> supplierNums(int num, Supplier<Integer> supplier) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list1.add(supplier.get());
        }
        return list1;
    }

    /**
     * Function<T,R> 示例
     * 字符串处理
     */
    @Test
    public void test3() {
        System.out.println(stringHandler("   xzw婷玉  \n",(str)-> str.trim()));
        System.out.println(stringHandler("xzw", (str) -> str + ",hello world!"));
    }

    private String stringHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * Predicate<T> 示例
     * 对集合中的满足条件的字符串过滤出来
     */
    @Test
    public void test4() {
        List<String> strs = Arrays.asList("xzw123", "ddfg", "zty456", "ztyxzw");
        opFilter(strs, (str) -> str.contains("xzw") || str.contains("zty")).forEach(System.out::println);
    }

    private List<String> opFilter(List<String> list, Predicate<String> predicate) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str)) {
                result.add(str);
            }
        }
        return result;
    }

    /**
     * 内置的其他函数式接口如下：
     */
    // 一、四大基类的入参扩展
    // BiFunction<T, U, R>  入参T U 返回R
    // BiConsumer<T, U> 入参T U 无返回
    // UnaryOperator<T, T> 一元运算，及 入参T 返回T 是Function的子接口
    // BinaryOperator<T>  二元运算，及 入参T1 T2 返回T 是BiFunction的子接口

    // 二、常用数据类型的接口
    // ToIntFunction<T> 入参为T 返回int类型
    // ToDoubleFunction<T> 入参为T 返回double类型
    // IntFunction<R> 入参是int 返回R
}
