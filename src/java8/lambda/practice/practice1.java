package lambda.practice;

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**、
 * 使用Java8内置的函数式接口完成练习
 */
public class practice1 {

    private List<Employee> getDatas() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("张三", 20, 2222));
        list.add(new Employee("李四", 30, 3333));
        list.add(new Employee("王五", 40, 4444));
        list.add(new Employee("赵六", 50, 5444));
        list.add(new Employee("田七", 30, 6444));
        return list;
    }

    /**
     * 练习一完成排序 先按年龄再按名字
     */
    @Test
    public void t1() {
        List<Employee> datas = getDatas();
        datas.sort((a, b) -> {
            if (a.getAge().equals(b.getAge())) {
                return a.getName().compareTo(b.getName());
            } else {
                return a.getAge().compareTo(b.getAge());
            }
        });
        datas.forEach(System.out::println);

        datas.subList(0, 1).forEach(System.out::println);
    }

    /**
     * 使用function完成字符串的转大写
     * 完成字符串的截取
     */
    @Test
    public void t2() {
        String str = "aaaa";
        String str1 = "1234";
        String apply = m1(str, (a) -> a.toUpperCase());
        System.out.println(apply);
        System.out.println(m1(str1, (b) -> b.substring(1, 3)));
    }

    private String m1(String param, Function<String,String> function) {
        return function.apply(param);
    }



}
