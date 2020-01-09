package lambda;

import lombok.Data;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
@Data
public class Employee {
    private String name;
    private Integer age;
    private Integer salary;

    public Employee(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
