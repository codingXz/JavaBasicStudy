package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 中间操作包括的基本部分如下：
 * 1.筛选和切片
 * 2.映射和排序
 *
 * 这节--流的中间操作第一部分 映射和排序
 * 1.map() 映射 接收lambda表达式作为参数，该函数会被应用到每个元素上，并生成一个新的元素
 * 2.flatMap() 将流扁平化的进行映射，将流中的每一个元素都转化为另一个流，最后把所有的流合成一个流
 * 3.sorted() 排序方法, 不传参数的话便会进行自然排序， 传参(Comparator 实现compareTo())
 */
public class StreamApiOperate2Test {

    /** map */
    @Test
    public void test1() {
        // 提取
        List<Employee> datas = Employee.getDatas();
        datas.stream().map(em -> em.getName()).forEach(System.out::println);

        // 转化成其他形式
        List<String> stringList = Arrays.asList("aa", "bb", "cc");
        stringList.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
    }

    /** flatMap */
    @Test
    public void test2() {
        // 问题1：将字符串的每一个字符都提取出来
        List<String> strList = Arrays.asList("asdfghj", "xzcvbn");
        List<String[]> collect1 = strList.stream().map(str -> str.split("")).collect(Collectors.toList());
        collect1.forEach(System.out::println);

        // flatMap - 接收的lambda表达式中(T 入参任意类型 、R 返回值必须要是Stream )
        List<String> collect2 = strList.stream().flatMap(StreamApiOperate2Test::filterCharacter).collect(Collectors.toList());
        collect2.forEach(System.out::println);

    }

    private static Stream<String> filterCharacter(String str) {
        String[] split = str.split("");
        return Arrays.stream(split);
    }

    /** sort */
    @Test
    public void test3() {
        List<String> strList = Arrays.asList("asdfghj", "xzcvbn");
        strList.stream().sorted().forEach(System.out::println);

        List<Employee> datas = Employee.getDatas();
        // 升序排序
        datas.stream().sorted(Comparator.comparing(Employee::getAge)).forEach(System.out::println);
    }

}
