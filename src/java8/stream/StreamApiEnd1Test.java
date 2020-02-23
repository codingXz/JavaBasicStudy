package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 流的终止操作 包括：
 * 1.查找与匹配
 * 2.规约与收集
 *
 * 这节--流的终止操作操作第一部分 查找与匹配
 * 方法包括：
 * allMatch() --是否所有的元素都匹配
 * anyMatch() --是否有部分元素匹配
 * noneMatch() --是否所有元素都不匹配
 * findFirst() --返回第一个元素
 * findAny() --返回任意一个元素
 * count() --返回流中元素的总个数
 * max() --返回流中最大的元素
 * min() --返回流中最小的元素
 */
public class StreamApiEnd1Test {

    /**
     * match演示
     */
    @Test
    public void test1() {
        List<String> strs = Arrays.asList("aa", "bb", "cc");
        List<String> strs2 = Arrays.asList("aa", "bb", "cc");
        String s = "aa";

        // allMatch(predicate) 流中是否所有的元素都符合predicate的条件
        System.out.println(strs.stream().allMatch(a -> strs2.contains(a)));

        // anyMatch() 流中是否存在元素符合predicate的条件
        System.out.println(strs.stream().anyMatch(a -> a.equals(s)));

        // noneMatch() 与allMatch相反 是否所有元素都不符合
        System.out.println(strs.stream().noneMatch(a -> strs2.contains(a)));
    }

    /**
     * findFirst,findAny 演示
     */
    @Test
    public void test2() {
        List<String> strs = Arrays.asList("aa", "bb", "cc");
        // 返回optional对象 不会出现空指针异常
        System.out.println(strs.stream().findFirst());

        System.out.println(strs.stream().findAny());
    }

    /**
     * count , max , min
     * 生成的流消费一次之后就不存在了
     */
    @Test
    public void test3() {
        List<Integer> ints = Arrays.asList(1, 3, 4, 6);
        System.out.println(ints.stream().count());

        // 返回optional对象 orElse对空值进行处理
        Optional<Integer> maxOp = ints.stream().max(Integer::compareTo);
        System.out.println(maxOp.orElse(null));

        System.out.println(ints.stream().min(Integer::compareTo).get());

    }
}
