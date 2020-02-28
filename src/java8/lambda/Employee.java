package lambda;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
@Data
@Builder
public class Employee {
    private String name;
    private Integer age;
    private Integer salary;

    public Employee() {
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public static List<Employee> getDatas() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("张三", 20, 2222));
        list.add(new Employee("李四", 30, 3333));
        list.add(new Employee("王五", 40, 4444));
        list.add(new Employee("赵六", 50, 5444));
        list.add(new Employee("田七", 60, 6444));
        return list;
    }
}
