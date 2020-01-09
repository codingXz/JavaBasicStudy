package api.compare;

import lombok.Data;

/**
 * 用于比较器的测试模型
 *
 * @Author: xzw
 * @Date: 2020/1/9
 */
@Data
public class Girl implements Comparable<Object>{

    private String name;
    private Integer age;

    Girl(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        Girl girl = (Girl) o;
        return this.age - girl.getAge();
    }
}
