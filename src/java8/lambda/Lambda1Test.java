package lambda;

import lambda.inteface.MyFunc;
import lambda.inteface.MyFuncStr;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 讲述、Lambda表示在1.7的版本上做了哪些改进
 *
 * lambda表达式的本质就是匿名内部类
 * 所以也遵循相同的规则：
 * 调用同级别的局部变量时，变量的值是不能被改变的，需要用final修饰
 * （在1.7时需要手动添加，而在1.8后编译器会自动添加）
 *
 * lambda表达式的语法规范：
 *  1.新增了一个lambda操作符"->"通过操作符将表达式分为两个部分 (a)->{a++}
 *  ()参数部分
 *  {}实现部分
 *
 * @author xzw
 */
public class Lambda1Test {

    /**
     * 问题一、要获得一个按一定顺序排序的set集合
     */

    /*使用匿名类*/
    @Test
    public void t1() {
        Comparator com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Set<Integer> set = new TreeSet<>(com);
        List<Integer> ints = Arrays.asList(3, 1, 8, 5, 0);
        set.addAll(ints);
        set.forEach(System.out::println);
    }

    /*使用lambda表达式*/
    @Test
    public void t2() {
        Set<Integer> set = new TreeSet<>((o1, o2) -> o2 - o1);
    }

    /**
     * 问题二、使用lambda表达式创建线程
     */
    @Test
    public void test3() {
        // 这个变量会被默认加上final修饰
        int num = 1;
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World" + num);
            }
        };
        run1.run();

        Runnable run2 = () -> System.out.println("Hello World");
        run2.run();
    }

    /**
     * 问题三、用lambda表达式实现两个泛型参数的接口
     */
    private void po(Long l1, Long l2, MyFunc<Long, Long> myFunc) {
        System.out.println(myFunc.getValue(l1, l2));
    }

    @Test
    public void test4() {
        po(10L, 25L, (a, b) -> a + b);
        po(10L, 25L, (a, b) -> (a + b)/b);
    }

    /**
     * 问题四、使用lambda表达式处理字符串
     */
    private void handleStr(String str, MyFuncStr funcStr) {
        System.out.println(funcStr.getValue(str));
    }
    @Test
    public void test5() {
        handleStr("肖淄文zty", (str) -> str.substring(0, 3));
        handleStr("肖淄文zty",(String::toUpperCase));
    }



}
