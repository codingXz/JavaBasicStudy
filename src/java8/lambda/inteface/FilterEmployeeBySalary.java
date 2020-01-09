package lambda.inteface;

import lambda.Employee;

/**
 * @Author: xzw
 * @Date: 2020/1/9
 */
public class FilterEmployeeBySalary implements MyPredicate {
    @Override
    public boolean test(Object o) {
        Employee o1 = (Employee) o;
        if (o1.getSalary() < 6000) {
            return true;
        }
        return false;
    }
}