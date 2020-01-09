package lambda;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用的示例
 * 格式：
 * ClassName::new
 * 注意：
 * 需要调用构造器的参数列表，必须与函数接口中的抽象方法的参数保持一致
 *
 *
 * 数组构造器的示例
 * 格式：
 * Type[]::new
 *
 * @Author: xzw
 */
public class TestConstructorRef {

    /**
     * 构造器函数引用的示例
     */
    @Test
    public void test1() {
        // 无参构造器的调用情况 Supplier只能用于无参情况
        Supplier<Employee> supplier = () -> new Employee();

        Supplier<Employee> sup1 = Employee::new;

        // 有参数的情况
        BiFunction<String, Integer, Employee> sup2 = (name, age) -> new Employee(name, age);

        BiFunction<String, Integer, Employee> sup3 = Employee::new;

    }

    /**
     * 数组构造器引用的示例
     */
    @Test
    public void test2() {
        Function<Integer,String[]> fun = (size) -> new String[size];

        Function<Integer, String[]> fun2 = String[]::new;

    }
}
