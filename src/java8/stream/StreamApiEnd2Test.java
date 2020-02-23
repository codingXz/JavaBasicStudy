package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流的终止操作 包括：
 * 1.查找与匹配
 * 2.规约与收集
 *
 * 这节讲第二部分 -- 规约与收集
 * 1.reduce() 规约 ，可以将流中的元素反复结合起来得到一个值
 * 2.collect(Collector) 收集（如收集到List，Set，Map） ，Collector对象为收集器实例。在Collectors中为我们提供了很多已经实现好的实列
 */
public class StreamApiEnd2Test {
    /**
     * reduce()操作
     */
    @Test
    public void test1() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //reduce(T identity, BinaryOperator<T> accumulator)  identity起始位置 ，需要进行的二元运算
        System.out.println(ints.stream().reduce(0, Integer::sum));

        //返回optional
        Optional<Integer> reduce = ints.stream().reduce(Integer::sum);
        System.out.println(reduce);

    }

    /**
     * 收集操作
     */
    @Test
    public void test2() {
        //1.转化为其他集合
        HashSet collect = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.toCollection(HashSet::new));

        //2.计算 统计数（如：总数，总和，平均值，最大值，最小值）
        Optional<Integer> maxCollect = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.maxBy(Integer::compareTo));
        Double avgCollect = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.averagingInt(Integer::intValue));
        Integer sumCollect = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.summingInt(Integer::intValue));
        Long countCollect = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.counting());

        //3.分组
        List<Employee> datas = Employee.getDatas();
        Map<Integer, List<Employee>> groupCollect = datas.stream().collect(Collectors.groupingBy(Employee::getAge));

        //4.多级分组
        Map<Integer, Map<String, List<Employee>>> collect1 =
                datas.stream().collect(Collectors.groupingBy(Employee::getAge,
                        Collectors.groupingBy(Employee::getName)));

        //5.分区
        Map<Boolean, List<Employee>> partitionCollect = datas.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 3000));

        //6.summarizing的使用
        IntSummaryStatistics stats = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("平均值：" + stats.getAverage());
        System.out.println("总计：" + stats.getSum());

        //7.对字符串进行处理 joining
        String stringJoin = Arrays.stream(new String[]{"1", "2", "3", "4"}).collect(Collectors.joining(",", "(", ")"));
        System.out.println(stringJoin);
    }
}
