package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream的三个基本操作步骤
 * <p>
 * 1.创建stream
 * <p>
 * 2.中间操作
 * <p>
 * 3.终止操作
 * <p>
 * 这个类主要讲到stream 的创建
 *
 * @Author: xzw
 */
public class StreamApi1Test {

    /**
     * 通过集合创建流
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        Stream<String> stream = list.stream();
    }

    /**
     * 通过数组创建流
     */
    @Test
    public void test2() {
        String[] strArr = new String[10];
        Stream<String> stream = Arrays.stream(strArr);
    }

    /**
     * 创建无限流
     */
    @Test
    public void test3() {
        // 使用静态方法创建流
        Stream<Integer> integerStream = Stream.of(1);

        // 使用用迭代的方式生成无线流 参数为 UnaryOperator
        Stream.iterate(10, (a) -> a + 2).limit(10).forEach(System.out::println);

        // 使用生成的方式产生无限流 参数为 supplier
        // Math.random() 的取值范围[0,1)
        Stream.generate(()->(int)(Math.random()*120)).limit(10).forEach(System.out::println);
    }
}
