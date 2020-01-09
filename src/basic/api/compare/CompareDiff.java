package api.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * comparator和comparable两个接口的区别和用法
 * 说明：
 * 1.Comparable接口实现类 是自身可比较的类 比较大小的方式就是通过实现的CompareTo() 相当于是内部比较器 代码的耦合性要强一点
 * 2.Comparator接口 外部比较器，在类没有实现Comparable时又想进行比较就使用Comparator 相当于是内部比较器
 *
 * @Author: xzw
 * @Date: 2020/1/9
 */
public class CompareDiff {

    public static void main(String[] args) {
        // girl对象按照年排序
        testComparable();

        testComparator();
    }

    private static void testComparable() {
        List<Girl> aList = new ArrayList<>();
        int listSize = 20;
        for (int i = 0; i < listSize; i++) {
            Girl girl = new Girl("girl" + i, i);
            aList.add(girl);
        }
        Collections.shuffle(aList);
        aList.forEach(System.out::println);

        System.out.println("-----Comparable排序后结果-----");
        Collections.sort(aList);
        // Collections.sort的底层是调用了对象的compareTo()进行比较
        aList.forEach(System.out::println);
    }

    private static void testComparator() {
        List<Girl> aList = new ArrayList<>();
        int listSize = 20;
        for (int i = 0; i < listSize; i++) {
            Girl girl = new Girl("girl" + i, i);
            aList.add(girl);
        }
        Collections.shuffle(aList);

        // 使用comparator进行排序
        Comparator comparator = new Comparator<Girl>() {
            @Override
            public int compare(Girl o1, Girl o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        aList.stream().sorted(comparator);
        System.out.println("-----Comparator排序后结果-----");
        aList.forEach(System.out::println);
    }
}
