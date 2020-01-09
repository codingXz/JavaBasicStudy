package lambda.inteface;

import lambda.Employee;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
public class FilterEmployeeByAge implements MyPredicate {
    @Override
    public boolean test(Object o) {
        Employee o1 = (Employee) o;
        if (o1.getAge() > 30) {
            return true;
        }
        return false;
    }
}
