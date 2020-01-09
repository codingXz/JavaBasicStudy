package lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 讲解方法引用和构造器引用
 *    衍生的原因：若lambda体中的方方已经实现了，可以采用方法引用的方式
 *
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类名::静态方法名
 *
 * 类名::实例方法名
 *
 * 使用的注意事项：
 * 1.引用方法的入参类型和 返回值类型必须 要与函数是接口中的方法入参类型和 返回值类型保持一致！（与1、2情况一致）
 *
 * 2.若lambda表达式的第一参数的实列方法的调用者 第二对象是实列方法的参数时可以使用 className::method
 *
 * @Author: xzw
 */
public class TestMethodRef {

    /**
     * 对象::实例方法名 示例
     * <p>
     * 1.通过consumer举例
     * <p>
     * 2.通过supplier举例
     **/
    @Test
    public void test1Consumer() {
        PrintStream out = System.out;
        Consumer<String> con = (str) -> out.println(str);
        con.accept("xzw.com");

        // 注意：对象::方法名 时引用方法的入参类型和 返回值类型必须和consumer.accept()一致！！！！！
        Consumer<String> con1 = out::println;
        con1.accept("xixixi");
    }

    @Test
    public void test1Supplier() {
        // 体现完整的lambda表达式和方法引用情况下的区别
        Employee employee = new Employee("小明", 22, 5000);
        Supplier<String> sup1 = () -> employee.getName();
        System.out.println(sup1.get());

        Supplier<Integer> sup2 = employee::getAge;
        System.out.println(sup2.get());
    }


    /**
     * 类名::静态方法名 与前面的方式规则相同
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (a, b) -> Integer.compare(a, b);

        Comparator<Integer> com2 = Integer::compare;
    }

    /**
     * 类名::实例方法名
     *
     * 比较两个字符串是否相同
     */
    @Test
    public void test3() {
        // (示例组一)即参数一必须为调用者 参数二为入参值
        BiPredicate<String, String> biPre = (str1, str2) -> str1.equals(str2);

        BiPredicate<String, String> biPre2 = String::equals;


        // (示例组二) 参数一必须为调用者
        Function<String, String> func = (str1) -> str1.trim();

        Function<String, String> func2 = String::trim;
    }

}
