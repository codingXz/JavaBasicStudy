package lambda;

import lambda.inteface.FilterEmployeeByAge;
import lambda.inteface.FilterEmployeeBySalary;
import lambda.inteface.MyPredicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda表达式是怎么 一步步进化而来的
 *
 * @Author: xzw
 * @Date: 2020/1/9
 */
public class Lambda2Test {

    private List<Employee> getDatas() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("张三", 20, 2222));
        list.add(new Employee("李四", 30, 3333));
        list.add(new Employee("王五", 40, 4444));
        list.add(new Employee("赵六", 50, 5444));
        list.add(new Employee("田七", 60, 6444));

        return list;
    }

    /**
     * 解决问题：
     * 1.从数据中找出年龄大于30
     * 2.从数据中找出薪资小于6000
     * 并且依次输出
     */

    /**
     * 方法一、使用最原始的方式
     * 带有的问题：出现大量代码的重复 只改动了其中的一小段代码
     */
    @Test
    public void test1() {
        List<Employee> datas = getDatas();
        List<Employee> result = new ArrayList<>();
        List<Employee> result2 = new ArrayList<>();

        // 龄大于30
        for (Employee data : datas) {
            if (data.getAge() > 30) {
                result.add(data);
            }
        }
        for (Employee employee : result) {
            System.out.println(employee);
        }

        System.out.println("----薪资小于6000-----");
        for (Employee data : datas) {
            if (data.getSalary() < 6000) {
                result2.add(data);
            }
        }
        for (Employee employee : result2) {
            System.out.println(employee);
        }

    }

    /**
     * 方法二、使用接口实现的方式
     */
    @Test
    public void test2() {
        List<Employee> datas = getDatas();
        FilterEmployeeByAge filter = new FilterEmployeeByAge();
        List<Employee> result = method1(datas, filter);
        for (Employee employee : result) {
            System.out.println(employee);
        }
        // 如果需要实现方法二就直接再写一个实现类
        System.out.println("----薪资小于6000-----");

        List<Employee> result2 = method1(datas, new FilterEmployeeBySalary());
        for (Employee employee : result2) {
            System.out.println(employee);
        }

    }

    /**
     *  可以将 datas，filter作为参数抽成一个公用的方法
     */
    private List<Employee> method1(List<Employee> datas, MyPredicate filter) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : datas) {
            if (filter.test(employee)) {
                result.add(employee);
            }
        }
        return result;
    }


    /**
     * 方法三使用匿名类实现的方式
     */
    @Test
    public void test3() {
        List<Employee> datas = getDatas();
        List<Employee> result = method1(datas, new MyPredicate() {
            @Override
            public boolean test(Object o) {
                Employee employee = (Employee) o;
                return employee.getAge() > 30;
            }
        });
        result.forEach(System.out::println);
    }

    /**
     * 方法四使用lambda的方式
     */
    @Test
    public void test4() {
        List<Employee> datas = getDatas();
        List<Employee> result = method1(datas, o -> {
            Employee employee = (Employee) o;
            return employee.getAge() > 30;
        });
        result.forEach(System.out::println);
    }

    /**
     * stream api的方式实现
     */
    @Test
    public void test5() {
        List<Employee> datas = getDatas();
        datas.stream().filter(e -> e.getAge() > 30).forEach(System.out::println);
        System.out.println("----薪资小于6000-----");
        datas.stream().filter(e -> e.getSalary() < 6000).forEach(System.out::println);
    }

}
