package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间操作包括的基本部分如下：
 * 1.筛选和切片
 * 2.映射和排序
 *
 * 这节--流的中间操作第一部分 筛选和切片
 * 1.filter() 接受lambda表达作为参数，过滤掉不满足条件的元素。
 * 2.limit() 截断流，使返回的元素不超过给定值
 * 3.skip() 跳过给定值个数的元素，流中的元素不足，则返回空流
 * 4.distinct() 去除流中重复的元素，通过equals() hashcode()比较
 */
public class StreamApiOperate1Test {

    private List<Employee> getDatas() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("张三", 20, 2222));
        list.add(new Employee("李四", 30, 3333));
        list.add(new Employee("王五", 40, 4444));
        list.add(new Employee("赵六", 50, 5444));
        list.add(new Employee("田七", 60, 6444));
        return list;
    }

    /** filter */
    @Test
    public void test1() {
        List<Employee> datas = getDatas();
        datas.stream().filter(em -> em.getSalary() > 3000).forEach(System.out::println);
    }

    /** limit */
    @Test
    public void test2() {
        List<Employee> datas = getDatas();
        datas.stream().filter(em -> em.getSalary() > 3000).limit(2).forEach(System.out::println);
    }

    /** skip */
    @Test
    public void test3() {
        List<Employee> datas = getDatas();
        datas.stream().filter(em -> em.getSalary() > 3000).skip(2).forEach(System.out::println);
    }

    /** distinct */
    @Test
    public void test4() {
        Employee xzw = Employee.builder().age(40).name("王五").salary(4444).build();
        List<Employee> datas = getDatas();
        datas.add(xzw);
        // 在进行重复值筛选时要进行equals() 和 hashcode的重写
        datas.stream().distinct().forEach(System.out::println);
    }
}
